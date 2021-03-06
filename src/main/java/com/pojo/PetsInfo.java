package com.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("领养宠物实体类")
public class PetsInfo {
    @JsonIgnore
    private Integer ap_id;
    @JsonIgnore
    private Integer pet_id;
    private Integer pi_id;
    private Integer base_id;
    private String pi_name,b_icon,b_name;
    private String pi_age;
    private String pi_breed;
    @ApiModelProperty("宠物疾病史")
    private String pi_DH;
    private String pi_intro;
    private String pi_image_1;
    private String pi_image_2;
    private String pi_image_3;
    private String pi_image_4;
    private String ap_status;
    private Date pi_createtime;
    private Date ap_pass_time;
}
