package com.service.impl;

import com.common.utils.RedisUtil;
import com.common.utils.TimeUtils;
import com.mapper.VolunteerMapper;
import com.pojo.ApplicationVolunteer;
import com.pojo.VolunteerForm;
import com.pojo.VolunteerInfo;
import com.pojo.vo.*;
import com.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BeamStark
 * @Version 0.1 2020/12
 */
@Service
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    private VolunteerMapper volunteerMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Integer userApplication(ApplicationVolunteer vo) {
        VolunteerInfo application = volunteerMapper.queryVolunteerInfoByBaseId(vo.getBase_id());
        String vi_status = volunteerMapper.queryVolunteerInfoByBaseId(vo.getBase_id()).getVi_status();

//        当前报名基地志愿者报名人数已满
        if (application.getVi_population() - application.getVi_joinPopulation() <= 0) {
            vo.setVi_status("已满");
            volunteerMapper.updateStatusByBaseId(vo);
            return 2;
        }
//        志愿者报名已截止
        if (!application.getVi_end_time().after(TimeUtils.getNowTime())) {
            vo.setVi_status("已截止");
            volunteerMapper.updateStatusByBaseId(vo);
            return 3;
        }
//        志愿者报名未开始
        if (application.getVi_start_time().after(TimeUtils.getNowTime())) {
            vo.setVi_status("未开始");
            volunteerMapper.updateStatusByBaseId(vo);
            return 4;
        }
        //        当前报名基地未开启志愿者报名
        if (vi_status.equals("不可报名") || application == null) {
            vo.setVi_status("不可报名");
            volunteerMapper.updateStatusByBaseId(vo);
            return 5;
        }
//        您已经报过名啦
        if (volunteerMapper.queryAppVolunteerById(vo.getUser_id()) != null) {
            return 6;
        }

        vo.setAv_status("待审批");
        vo.setVi_status("可报名");
        vo.setAv_application_time(TimeUtils.getNowTime());
        volunteerMapper.updateJoinPopulationByBaseId(vo.getBase_id());
        volunteerMapper.createVolunteerForm(vo);
        volunteerMapper.userApplication(vo);
        volunteerMapper.updateStatusByBaseId(vo);
        return 1;
    }

    @Override
    public QueryVolunteerByIdVo queryAppVolunteerById(Integer user_id) {
        return volunteerMapper.queryAppVolunteerById(user_id);
    }

    @Override
    public VolunteerInfo queryVolunteerInfoByBaseId(Integer base_id) {
        return volunteerMapper.queryVolunteerInfoByBaseId(base_id);
    }

    @Override
    public void createVolunteerForm(VolunteerForm volunteerForm) {
        volunteerForm.setVf_create_time(TimeUtils.getNowTime());
        volunteerMapper.createVolunteerForm(volunteerForm);
        redisUtil.del("allPopularizations");
    }

    @Override
    public Object queryAllVolunteer() {
//        if (redisUtil.hasKey("allVolunteer"))
//            return redisUtil.get("allVolunteer");
//        else
//            redisUtil.set("allVolunteer", volunteerMapper.queryAllVolunteer(), 30);
//        return redisUtil.get("allVolunteer");
        return volunteerMapper.queryAllVolunteer();
    }

    @Override
    public void cancelApplicationVolunteer(UserIdVo vo) {
        volunteerMapper.delApplicatFormByUserId(vo);
        volunteerMapper.delApplicatVolunteerByUserId(vo);
        redisUtil.del("allPopularizations");
    }

    @Override
    public List<VolunteerForm> queryVolunteerFrom() {
        return volunteerMapper.queryVolunteerFrom();
    }

    @Override
    public void updateAVStatusByid(ApplicationVo applicationVo) {
        volunteerMapper.updateAVStatusByid(applicationVo);
        redisUtil.del("allPopularizations");
    }

    @Override
    public void updateAVPassTimeByid(ApplicationVo applicationVo) {
        volunteerMapper.updateAVPassTimeByid(applicationVo);
        redisUtil.del("allPopularizations");
    }

    @Override
    public List<AvFormVo> queryAvFormByStatus(AvStatusVo vo) {
        return volunteerMapper.queryAvFormByStatus(vo);
    }

    @Override
    public void changeVolunteerInfo(VolunteerInfoVo vo) {
        volunteerMapper.changeVolunteerInfo(vo);
    }

    @Override
    public void createVolunteerInfo(VolunteerInfo po) {
        po.setVi_create_time(TimeUtils.getNowTime());
        po.setVi_status("待审批");
        volunteerMapper.createVolunteerInfo(po);
    }

    @Override
    public void delVolunteerInfoByid(VolunteerInfoIdVo vo) {
        volunteerMapper.delVolunteerInfoByid(vo);
    }
}