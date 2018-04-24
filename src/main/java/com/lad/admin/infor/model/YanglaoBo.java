package com.lad.admin.infor.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Document(collection="yanglao")
public class YanglaoBo extends ResultBo implements Serializable {

}
