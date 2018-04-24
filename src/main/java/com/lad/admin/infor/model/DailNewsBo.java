package com.lad.admin.infor.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Document(collection="dailynews")
public class DailNewsBo extends ResultBo implements Serializable {

}
