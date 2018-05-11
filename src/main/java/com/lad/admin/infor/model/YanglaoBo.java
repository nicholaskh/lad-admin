package com.lad.admin.infor.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@Document(collection="yanglao")
@ToString
public class YanglaoBo extends ResultBo implements Serializable {

}
