package com.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("科普实体类")
public class Popularizations {
    private String p_title;
    private String p_details;
    private String p_author;
    @JsonIgnore
    private Integer p_pv;
    @JsonIgnore
    private Date p_createtime;
    @JsonIgnore
    private Date p_updatetime;
    @ApiModelProperty("如果没有图片可以不传")
    private String p_image;
}