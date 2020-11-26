package com.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserApplicationVo {
    @NotBlank(message = "用户名不能为空")
    private String user_name;
    @NotNull(message = "报名id不能为空")
    private Integer b_id;
    @JsonIgnore
    private Integer b_joinPopulation;
    @JsonIgnore
    private Integer b_population;
    @JsonIgnore
    private Integer b_status;
}
