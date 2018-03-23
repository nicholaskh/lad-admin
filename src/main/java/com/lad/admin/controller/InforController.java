package com.lad.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lad.admin.infor.model.HealthBo;
import com.lad.admin.infor.model.RadioBo;
import com.lad.admin.infor.model.SecurityBo;
import com.lad.admin.infor.model.VideoBo;
import com.lad.admin.service.IHealthService;
import com.lad.admin.service.IRadioService;
import com.lad.admin.service.ISecuritySerivce;
import com.lad.admin.service.IVideoService;
import com.lad.admin.utils.CommUtils;
import com.lad.admin.vo.HealthVo;
import com.lad.admin.vo.RadioVo;
import com.lad.admin.vo.SecurityVo;
import com.lad.admin.vo.VideoVo;
import com.mongodb.WriteResult;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/3/20
 */
@RestController
@RequestMapping("/infor")
@Api("资讯编辑Controller相关api")
public class InforController extends BaseController {

    /**
     * 健康
     */
    public static final String HEALTH_NAME = "healthTypes";
    /**
     * 安防
     */
    public static final String SECRITY_NAME = "securityTypes";
    /**
     * 广播
     */
    public static final String RADIO_NAME = "radioTypes";
    /**
     * 视频
     */
    public static final String VIDEO_NAME = "videoTypes";

    @Autowired
    private IHealthService healthService;

    @Autowired
    private ISecuritySerivce securitySerivce;

    @Autowired
    private IRadioService radioService;

    @Autowired
    private IVideoService videoService;


    @ApiOperation(value="获取健康资讯详细信息", notes="通过inforid获取健康资讯详细信息", response = HealthVo.class)
    @ApiImplicitParam(name = "inforid", value = "资讯ID", required = true, paramType="path",  dataType = "string")
    @GetMapping("/health/get/{inforid}")
    public HealthVo getHealth(@PathVariable String inforid){
        HealthBo healthBo = healthService.findById(inforid);
        HealthVo healthVo = null;
        if (healthBo != null) {
            healthVo = new HealthVo();
            BeanUtils.copyProperties(healthBo, healthVo);
            healthVo.setInforid(healthBo.getId());
        }
        return healthVo;
    }


    @ApiOperation(value="添加健康资讯", notes="添加健康资讯")
    @PostMapping("/health/insert")
    public String insertHealth(@RequestBody @ApiParam(name="healthVo",value="健康资讯实体类",required=true)HealthVo healthVo){
        HealthBo healthBo = new HealthBo();
        BeanUtils.copyProperties(healthVo, healthBo);
        healthService.insert(healthBo);
        return setSuccessResp("inforid", healthBo.getId());
    }


    @ApiOperation(value="修改健康资讯", notes="修改资讯信息")
    @PutMapping("/health/update")
    public String updateHealth(@RequestBody @ApiParam(name="healthVo",value="健康资讯实体类，只传入需要修改的对象及inforid",
            required=true) HealthVo healthVo){
        if (healthVo == null || StringUtils.isEmpty(healthVo.getInforid())) {
            return setErrorResp(-1, "资讯inforid为空");
        }
        JSONObject jsonObject = (JSONObject)JSON.toJSON(healthVo);
        Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
        Map<String, Object> params = new LinkedHashMap<>();
        while (iterator.hasNext()){
            Map.Entry<String, Object> entry =  iterator.next();
            if (entry.getValue() != null) {
                params.put(entry.getKey(), entry.getValue());
            }
        }
        if (params.size() > 0) {
            healthService.saveByParams(healthVo.getInforid(), params);
        }
        return setSuccessResp("inforid", healthVo.getInforid());
    }



    @ApiOperation(value="删除指定健康资讯")
    @ApiImplicitParam(name = "inforid", value = "资讯ID", required = true, paramType="path",  dataType = "string")
    @DeleteMapping("/health/delete/{inforid}")
    public String deleteHealth(@PathVariable String inforid){
        WriteResult result = healthService.deleteById(inforid);
        if (result.isUpdateOfExisting()){
            return COM_RESP;
        }
        return setErrorResp(-1, "删除失败");
    }


    @ApiOperation(value="删除指定健康资讯")
    @ApiImplicitParam(name = "inforids", value = "资讯ID，多个以逗号隔开", required = true, paramType="path",  dataType =
            "string")
    @DeleteMapping("/health/batchDelete/{inforids}")
    public String batchDeleteHealth(@PathVariable String inforids){
        if (StringUtils.isEmpty(inforids)) {
            return setErrorResp(-1, "删除资讯ID为空");
        }
        String[] inforidArr = CommUtils.getSplitValues(inforids);
        WriteResult result = healthService.deleteByIds(inforidArr);
        if (result.isUpdateOfExisting()){
            return COM_RESP;
        }
        return setErrorResp(-1, "删除失败");
    }


    @ApiOperation(value="根据关键字搜索健康咨询", notes="根据关键字搜索健康咨询", response = HealthVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", required = true, paramType="path",  dataType =
                    "string"),
            @ApiImplicitParam(name = "page", value = "页面", required = true, paramType="path",  dataType =
                    "int"),
            @ApiImplicitParam(name = "limit", value = "每页显示条数", required = true, paramType="path",  dataType =
                    "int")
    })
    @GetMapping("/health-search/{keyword}/{page}/{limit}")
    public List<HealthVo> findHealthByKeyword(@PathVariable String keyword, @PathVariable Integer page, @PathVariable Integer
            limit){
        List<HealthBo> healthBos = healthService.findByKeyword(keyword, page, limit);
        List<HealthVo> healthVos = new LinkedList<>();
        for (HealthBo healthBo : healthBos) {
            HealthVo healthVo = new HealthVo();
            BeanUtils.copyProperties(healthBo, healthVo);
            healthVo.setInforid(healthBo.getId());
            healthVo.setText("");
            healthVos.add(healthVo);
        }
        return healthVos;
    }


    @ApiOperation(value="根据分类查询健康咨询", notes="根据分类查询健康咨询", response = HealthVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "module", value = "module分类名称", required = true, paramType="path",  dataType =
                    "string"),
            @ApiImplicitParam(name = "page", value = "页面", required = true, paramType="path",  dataType =
                    "int"),
            @ApiImplicitParam(name = "limit", value = "每页显示条数", required = true, paramType="path",  dataType =
                    "int")
    })
    @GetMapping("/health/module/{module}/{page}/{limit}")
    public List<HealthVo> findHealthByModule(@PathVariable String module, @PathVariable Integer page, @PathVariable
            Integer limit){
        List<HealthBo> healthBos = healthService.findByModuleClassName(module,"", page, limit);
        List<HealthVo> healthVos = new LinkedList<>();
        for (HealthBo healthBo : healthBos) {
            HealthVo healthVo = new HealthVo();
            BeanUtils.copyProperties(healthBo, healthVo);
            healthVo.setInforid(healthBo.getId());
            healthVo.setText("");
            healthVos.add(healthVo);
        }
        return healthVos;
    }


    @ApiOperation(value="查询所有健康咨询", notes="查询所有健康咨询", response = HealthVo.class)
    @GetMapping("/health/{page}/{limit}")
    public List<HealthVo> findHealths(@PathVariable Integer page, @PathVariable Integer limit){
        List<HealthBo> healthBos = healthService.findAll(page, limit);
        List<HealthVo> healthVos = new LinkedList<>();
        for (HealthBo healthBo : healthBos) {
            HealthVo healthVo = new HealthVo();
            BeanUtils.copyProperties(healthBo, healthVo);
            healthVo.setInforid(healthBo.getId());
            healthVo.setText("");
            healthVos.add(healthVo);
        }
        return healthVos;
    }

    @ApiOperation(value="合并健康咨询module分类", notes="合并健康咨询module分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "moduleOle", value = "被合并module分类名称", required = true, paramType="path",
                    dataType = "string"),
            @ApiImplicitParam(name = "moduleNew", value = "合并后的module分类名称", required = true, paramType="path",
                    dataType = "string")
    })
    @PutMapping("/health/merge/{moduleOld}/{moduleNew}")
    public String findHealthByModule(@PathVariable String moduleOld, @PathVariable String moduleNew){
        healthService.updateModule(moduleOld, moduleNew);
        return setSuccessResp("resp", "合并成功");
    }



    @ApiOperation(value="获取安防资讯详细信息", notes="通过inforid获取安防资讯详细信息", response = SecurityVo.class)
    @ApiImplicitParam(name = "inforid", value = "资讯ID", required = true, paramType="path",  dataType = "string")
    @GetMapping("/security/get/{inforid}")
    public SecurityVo getSecurity(@PathVariable String inforid){
        SecurityBo securityBo = securitySerivce.findById(inforid);
        SecurityVo securityVo = null;
        if (securityBo != null) {
            securityVo = new SecurityVo();
            BeanUtils.copyProperties(securityBo, securityVo);
            securityVo.setInforid(securityBo.getId());
        }
        return securityVo;
    }


    @ApiOperation(value="添加安防资讯", notes="添加安防资讯")
    @PostMapping("/security/insert")
    public String insertSecurity(@RequestBody @ApiParam(name="securityVo",value="安防资讯实体类",required=true)SecurityVo
                                           securityVo){
        SecurityBo securityBo = new SecurityBo();
        BeanUtils.copyProperties(securityVo, securityBo);
        securitySerivce.insert(securityBo);
        return setSuccessResp("inforid", securityBo.getId());
    }


    @ApiOperation(value="修改安防资讯", notes="修改安防资讯信息")
    @PutMapping("/security/update")
    public String updateSecurity(@RequestBody @ApiParam(name="securityVo",value="安防资讯实体类，只传入需要修改的数据项及inforid",
            required=true) SecurityVo securityVo){
        if (securityVo == null || StringUtils.isEmpty(securityVo.getInforid())) {
            return setErrorResp(-1, "资讯inforid为空");
        }
        JSONObject jsonObject = (JSONObject)JSON.toJSON(securityVo);
        Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
        Map<String, Object> params = new LinkedHashMap<>();
        while (iterator.hasNext()){
            Map.Entry<String, Object> entry =  iterator.next();
            if (entry.getValue() != null) {
                params.put(entry.getKey(), entry.getValue());
            }
        }
        if (params.size() > 0) {
            securitySerivce.saveByParams(securityVo.getInforid(), params);
        }
        return setSuccessResp("inforid", securityVo.getInforid());
    }



    @ApiOperation(value="删除指定安防资讯")
    @ApiImplicitParam(name = "inforid", value = "资讯ID", required = true, paramType="path",  dataType = "string")
    @DeleteMapping("/security/delete/{inforid}")
    public String deleteSecurity(@PathVariable String inforid){
        WriteResult result = securitySerivce.deleteById(inforid);
        if (result.isUpdateOfExisting()){
            return COM_RESP;
        }
        return setErrorResp(-1, "删除失败");
    }


    @ApiOperation(value="删除安防资讯")
    @ApiImplicitParam(name = "inforids", value = "资讯ID，多个以逗号隔开", required = true, paramType="path",  dataType =
            "string")
    @DeleteMapping("/security/batchDelete/{inforids}")
    public String batchDeleteSecurity(@PathVariable String inforids){
        if (StringUtils.isEmpty(inforids)) {
            return setErrorResp(-1, "删除资讯ID为空");
        }
        String[] inforidArr = CommUtils.getSplitValues(inforids);
        WriteResult result = securitySerivce.deleteByIds(inforidArr);
        if (result.isUpdateOfExisting()){
            return COM_RESP;
        }
        return setErrorResp(-1, "删除失败");
    }


    @ApiOperation(value="根据关键字搜索安防咨询", notes="根据关键字搜索安防咨询", response = SecurityVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", required = true, paramType="path",  dataType =
                    "string"),
            @ApiImplicitParam(name = "page", value = "页面", required = true, paramType="path",  dataType =
                    "int"),
            @ApiImplicitParam(name = "limit", value = "每页显示条数", required = true, paramType="path",  dataType =
                    "int")
    })
    @GetMapping("/security-search/{keyword}/{page}/{limit}")
    public List<SecurityVo> findSecurityByKeyword(@PathVariable String keyword, @PathVariable Integer page, @PathVariable Integer
            limit){
        List<SecurityBo> securityBos = securitySerivce.findByKeyword(keyword, page, limit);
        List<SecurityVo> securityVos = new LinkedList<>();
        for (SecurityBo securityBo : securityBos) {
            SecurityVo securityVo = new SecurityVo();
            BeanUtils.copyProperties(securityBo, securityVo);
            securityVo.setInforid(securityBo.getId());
            securityVo.setText("");
            securityVos.add(securityVo);
        }
        return securityVos;
    }

    
    @ApiOperation(value="根据分类安防咨询", notes="根据分类查询安防咨询", response = SecurityVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newstype", value = "newstype分类名称", required = true, paramType="path",  dataType =
                    "string"),
            @ApiImplicitParam(name = "page", value = "页面", required = true, paramType="path",  dataType =
                    "int"),
            @ApiImplicitParam(name = "limit", value = "每页显示条数", required = true, paramType="path",  dataType =
                    "int")
    })
    @GetMapping("/security/newstype/{newstype}/{page}/{limit}")
    public List<SecurityVo> findSecurityByModule(@PathVariable String newstype, @PathVariable Integer page,
                                                 @PathVariable Integer limit){
        List<SecurityBo> securityBos = securitySerivce.findByNewType(newstype, page, limit);
        List<SecurityVo> securityVos = new LinkedList<>();
        for (SecurityBo healthBo : securityBos) {
            SecurityVo healthVo = new SecurityVo();
            BeanUtils.copyProperties(healthBo, healthVo);
            healthVo.setInforid(healthBo.getId());
            healthVo.setText("");
            securityVos.add(healthVo);
        }
        return securityVos;
    }


    @ApiOperation(value="查询所有安防咨询", notes="查询所有安防咨询", response = SecurityVo.class)
    @GetMapping("/security/{page}/{limit}")
    public List<SecurityVo> findSecuritys(@PathVariable String newstype, @PathVariable Integer page,
                                                 @PathVariable Integer limit){
        List<SecurityBo> securityBos = securitySerivce.findAll(page, limit);
        List<SecurityVo> securityVos = new LinkedList<>();
        for (SecurityBo healthBo : securityBos) {
            SecurityVo healthVo = new SecurityVo();
            BeanUtils.copyProperties(healthBo, healthVo);
            healthVo.setInforid(healthBo.getId());
            healthVo.setText("");
            securityVos.add(healthVo);
        }
        return securityVos;
    }



    @ApiOperation(value="合并安防newsType分类", notes="合并安防newsType分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newstypeOld", value = "被合并newsType分类名称", required = true, paramType="path",
                    dataType = "string"),
            @ApiImplicitParam(name = "newstypeNew", value = "合并后的newsType分类名称", required = true, paramType="path",
                    dataType = "string")
    })
    @PutMapping("/security/merge/{newstypeOld}/{newstypeNew}")
    public String mergeType(@PathVariable String newstypeOld, @PathVariable String newstypeNew){
        securitySerivce.updateModule(newstypeOld, newstypeNew);
        return COM_RESP;
    }



    @ApiOperation(value="获取广播资讯详细信息", notes="通过inforid获取广播详细信息", response = RadioVo.class)
    @ApiImplicitParam(name = "inforid", value = "资讯ID", required = true, paramType="path",  dataType = "string")
    @GetMapping("/radio/get/{inforid}")
    public RadioVo getRadio(@PathVariable String inforid){
        RadioBo radioBo = radioService.findById(inforid);
        RadioVo radioVo = null;
        if (radioBo != null) {
            radioVo = new RadioVo();
            BeanUtils.copyProperties(radioBo, radioVo);
            radioVo.setInforid(radioBo.getId());
        }
        return radioVo;
    }


    @ApiOperation(value="添加广播资讯", notes="添加广播资讯")
    @PostMapping("/radio/insert")
    public String insertRadio(@RequestBody @ApiParam(name="radioVo",value="广播资讯实体类",required=true) RadioVo
                                         radioVo){
        RadioBo radioBo = new RadioBo();
        BeanUtils.copyProperties(radioVo, radioBo);
        radioService.insert(radioBo);
        return setSuccessResp("inforid", radioBo.getId());
    }


    @ApiOperation(value="修改广播资讯", notes="修改广播资讯信息")
    @PutMapping("/radio/update")
    public String updateRadio(@RequestBody @ApiParam(name="radioVo",value="广播资讯实体类，只传入需要修改的数据项及inforid",
            required=true) RadioVo radioVo){
        if (radioVo == null || StringUtils.isEmpty(radioVo.getInforid())) {
            return setErrorResp(-1, "资讯inforid为空");
        }
        JSONObject jsonObject = (JSONObject)JSON.toJSON(radioVo);
        Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
        Map<String, Object> params = new LinkedHashMap<>();
        while (iterator.hasNext()){
            Map.Entry<String, Object> entry =  iterator.next();
            if (entry.getValue() != null) {
                params.put(entry.getKey(), entry.getValue());
            }
        }
        if (params.size() > 0) {
            radioService.saveByParams(radioVo.getInforid(), params);
        }
        return setSuccessResp("inforid", radioVo.getInforid());
    }



    @ApiOperation(value="删除指定广播资讯")
    @ApiImplicitParam(name = "inforid", value = "资讯ID", required = true, paramType="path",  dataType = "string")
    @DeleteMapping("/radio/delete/{inforid}")
    public String deleteRadio(@PathVariable String inforid){
        WriteResult result = radioService.deleteById(inforid);
        if (result.isUpdateOfExisting()){
            return COM_RESP;
        }
        return setErrorResp(-1, "删除失败");
    }


    @ApiOperation(value="删除广播资讯")
    @ApiImplicitParam(name = "inforids", value = "资讯ID，多个以逗号隔开", required = true, paramType="path",  dataType =
            "string")
    @DeleteMapping("/radio/batchDelete/{inforids}")
    public String batchDeleteRadio(@PathVariable String inforids){
        if (StringUtils.isEmpty(inforids)) {
            return setErrorResp(-1, "删除资讯ID为空");
        }
        String[] inforidArr = CommUtils.getSplitValues(inforids);
        WriteResult result = radioService.deleteByIds(inforidArr);
        if (result.isUpdateOfExisting()){
            return COM_RESP;
        }
        return setErrorResp(-1, "删除失败");
    }


    @ApiOperation(value="根据关键字搜索广播咨询", notes="根据关键字搜索广播咨询", response = RadioVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", required = true, paramType="path",  dataType =
                    "string"),
            @ApiImplicitParam(name = "page", value = "页面", required = true, paramType="path",  dataType =
                    "int"),
            @ApiImplicitParam(name = "limit", value = "每页显示条数", required = true, paramType="path",  dataType =
                    "int")
    })
    @GetMapping("/radio-search/{keyword}/{page}/{limit}")
    public List<RadioVo> findRadioByKeyword(@PathVariable String keyword, @PathVariable Integer page, @PathVariable Integer
            limit){
        List<RadioBo> radioBos = radioService.findByKeyword(keyword, page, limit);
        List<RadioVo> radioVos = new LinkedList<>();
        for (RadioBo radioBo : radioBos) {
            RadioVo radioVo = new RadioVo();
            BeanUtils.copyProperties(radioBo, radioVo);
            radioVo.setInforid(radioBo.getId());
            radioVos.add(radioVo);
        }
        return radioVos;
    }


    @ApiOperation(value="查找广播咨询module分类", notes="查找广播咨询module分类")
    @GetMapping("/radio/modules")
    public String findRadioModules(){
        List<RadioBo> radioBos = radioService.findModules();
        HashSet<String> broadTypes = new LinkedHashSet<>();
        radioBos.forEach( radioBo -> {broadTypes.add(radioBo.getId());});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(RADIO_NAME, broadTypes);
        return jsonObject.toJSONString();
    }

    @ApiOperation(value="查找module下的className分类", notes="查找广播咨询module分类")
    @GetMapping("/radio/groups")
    public String findRadioGroups(@PathVariable String module){
        List<RadioBo> radioBos = radioService.findClassByModule(module);
        HashSet<String> broadTypes = new LinkedHashSet<>();
        radioBos.forEach( radioBo -> {broadTypes.add(radioBo.getClassName());});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("radioGroups", broadTypes);
        return jsonObject.toJSONString();
    }


    @ApiOperation(value="查询所有广播咨询", notes="查询广播防咨询", response = RadioVo.class)
    @GetMapping("/radio/{page}/{limit}")
    public List<RadioVo> findRadios(@PathVariable Integer page, @PathVariable Integer limit){
        List<RadioBo> radioBos = radioService.findAll(page, limit);
        List<RadioVo> radioVos = new LinkedList<>();
        for (RadioBo radioBo : radioBos) {
            RadioVo radioVo = new RadioVo();
            BeanUtils.copyProperties(radioBo, radioVo);
            radioVo.setInforid(radioBo.getId());
            radioVos.add(radioVo);
        }
        return radioVos;
    }



    @ApiOperation(value="合并广播module分类", notes="合并广播module分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "moduleOld", value = "被合并moduleOld分类名称", required = true, paramType="path",
                    dataType = "string"),
            @ApiImplicitParam(name = "moduleNew", value = "合并后的moduleNew分类名称", required = true, paramType="path",
                    dataType = "string")
    })
    @PutMapping("/radio/merge/{moduleOld}/{moduleNew}")
    public String mergeRadio(@PathVariable String moduleOld, @PathVariable String moduleNew){
        radioService.updateModule(moduleOld, moduleNew);
        return COM_RESP;
    }


    @ApiOperation(value="获取视频资讯详细信息", notes="通过inforid获取视频详细信息", response = VideoVo.class)
    @ApiImplicitParam(name = "inforid", value = "资讯ID", required = true, paramType="path",  dataType = "string")
    @GetMapping("/video/get/{inforid}")
    public VideoVo getVideo(@PathVariable String inforid){
        VideoBo videoBo = videoService.findById(inforid);
        VideoVo videoVo = null;
        if (videoBo != null) {
            videoVo = new VideoVo();
            BeanUtils.copyProperties(videoBo, videoVo);
            videoVo.setInforid(videoBo.getId());
        }
        return videoVo;
    }


    @ApiOperation(value="添加视频资讯", notes="添加视频资讯")
    @PostMapping("/video/insert")
    public String insertVideo(@RequestBody @ApiParam(name="videoVo",value="广播资讯实体类",required=true) VideoVo videoVo){
        VideoBo videoBo = new VideoBo();
        BeanUtils.copyProperties(videoVo, videoBo);
        videoService.insert(videoBo);
        return setSuccessResp("inforid", videoBo.getId());
    }


    @ApiOperation(value="修改视频资讯", notes="修改视频资讯信息")
    @PutMapping("/video/update")
    public String updateVideo(@RequestBody @ApiParam(name="videoVo",value="视频资讯实体类，只传入需要修改的数据项及inforid",
            required=true) VideoVo videoVo){
        if (videoVo == null || StringUtils.isEmpty(videoVo.getInforid())) {
            return setErrorResp(-1, "资讯inforid为空");
        }
        JSONObject jsonObject = (JSONObject)JSON.toJSON(videoVo);
        Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
        Map<String, Object> params = new LinkedHashMap<>();
        while (iterator.hasNext()){
            Map.Entry<String, Object> entry =  iterator.next();
            if (entry.getValue() != null) {
                params.put(entry.getKey(), entry.getValue());
            }
        }
        if (params.size() > 0) {
            videoService.saveByParams(videoVo.getInforid(), params);
        }
        return setSuccessResp("inforid", videoVo.getInforid());
    }



    @ApiOperation(value="删除指定视频资讯")
    @ApiImplicitParam(name = "inforid", value = "资讯ID", required = true, paramType="path",  dataType = "string")
    @DeleteMapping("/video/delete/{inforid}")
    public String deleteVideo(@PathVariable String inforid){
        WriteResult result = videoService.deleteById(inforid);
        if (result.isUpdateOfExisting()){
            return COM_RESP;
        }
        return setErrorResp(-1, "删除失败");
    }


    @ApiOperation(value="删除视频资讯")
    @ApiImplicitParam(name = "inforids", value = "资讯ID，多个以逗号隔开", required = true, paramType="path",  dataType =
            "string")
    @DeleteMapping("/video/batchDelete/{inforids}")
    public String batchDeleteVideo(@PathVariable String inforids){
        if (StringUtils.isEmpty(inforids)) {
            return setErrorResp(-1, "删除资讯ID为空");
        }
        String[] inforidArr = CommUtils.getSplitValues(inforids);
        WriteResult result = videoService.deleteByIds(inforidArr);
        if (result.isUpdateOfExisting()){
            return COM_RESP;
        }
        return setErrorResp(-1, "删除失败");
    }


    @ApiOperation(value="根据关键字搜索视频咨询", notes="根据关键字搜索视频咨询", response = VideoVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", required = true, paramType="path",  dataType =
                    "string"),
            @ApiImplicitParam(name = "page", value = "页面", required = true, paramType="path",  dataType =
                    "int"),
            @ApiImplicitParam(name = "limit", value = "每页显示条数", required = true, paramType="path",  dataType =
                    "int")
    })
    @GetMapping("/video-search/{keyword}/{page}/{limit}")
    public List<VideoVo> findVideoByKeyword(@PathVariable String keyword, @PathVariable Integer page, @PathVariable
            Integer limit){
        List<VideoBo> videoBos = videoService.findByKeyword(keyword, page, limit);
        List<VideoVo> videoVos = new LinkedList<>();
        for (VideoBo videoBo : videoBos) {
            VideoVo videoVo = new VideoVo();
            BeanUtils.copyProperties(videoBo, videoVo);
            videoVo.setInforid(videoBo.getId());
            videoVos.add(videoVo);
        }
        return videoVos;
    }


    @ApiOperation(value="查找视频咨询module分类", notes="查找视频咨询module分类")
    @GetMapping("/video/modules")
    public String findVideoModules(){
        List<VideoBo> videoBos = videoService.findModules();
        HashSet<String> broadTypes = new LinkedHashSet<>();
        videoBos.forEach( videoBo -> {broadTypes.add(videoBo.getId());});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(VIDEO_NAME, broadTypes);
        return jsonObject.toJSONString();
    }

    @ApiOperation(value="查找module下的className分类", notes="查找视频咨询module分类")
    @GetMapping("/video/groups")
    public String findVideoGroups(@PathVariable String module){
        List<VideoBo> videoBos = videoService.findClassByModule(module);
        HashSet<String> broadTypes = new LinkedHashSet<>();
        videoBos.forEach( videoBo -> {broadTypes.add(videoBo.getClassName());});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("videoGroups", broadTypes);
        return jsonObject.toJSONString();
    }


    @ApiOperation(value="查询所有视频咨询", notes="查询视频咨询", response = VideoVo.class)
    @GetMapping("/video/{page}/{limit}")
    public List<VideoVo> findVideos(@PathVariable Integer page, @PathVariable Integer limit){
        List<VideoBo> videoBos = videoService.findAll(page, limit);
        List<VideoVo> videoVos = new LinkedList<>();
        for (VideoBo videoBo : videoBos) {
            VideoVo videoVo = new VideoVo();
            BeanUtils.copyProperties(videoBo, videoVo);
            videoVo.setInforid(videoBo.getId());
            videoVos.add(videoVo);
        }
        return videoVos;
    }



    @ApiOperation(value="合并视频module分类", notes="合并视频module分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "moduleOld", value = "被合并moduleOld分类名称", required = true, paramType="path",
                    dataType = "string"),
            @ApiImplicitParam(name = "moduleNew", value = "合并后的moduleNew分类名称", required = true, paramType="path",
                    dataType = "string")
    })
    @PutMapping("/video/merge/{moduleOld}/{moduleNew}")
    public String mergeVideo(@PathVariable String moduleOld, @PathVariable String moduleNew){
        videoService.updateModule(moduleOld, moduleNew);
        return COM_RESP;
    }
    
}
