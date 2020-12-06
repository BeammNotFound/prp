package com.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("志愿者实体类")
public class VolunteerInfo {

    @JsonIgnore
    private Integer vi_id;

    private Integer base_id;

    @ApiModelProperty("志愿者状态")
    private String vi_status;

    private String vi_title;
    private String vi_intro;
    private Integer vi_joinPopulation;
    private Integer vi_population;

    private Date vi_end_time;
    private Date vi_start_time;
    private Date vi_create_time;

}
