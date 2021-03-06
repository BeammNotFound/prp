package com.mapper;

import com.pojo.ApplicationVolunteer;
import com.pojo.VolunteerForm;
import com.pojo.VolunteerInfo;
import com.pojo.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author BeamStark
 * @Version 0.1 2020/12
 */
@Mapper
@Repository

public interface VolunteerMapper {
    void userApplication(ApplicationVolunteer applicationVo);
    QueryVolunteerByIdVo queryAppVolunteerById(Integer user_id);

    VolunteerInfo queryVolunteerInfoByBaseId(Integer base_id);

    void updateJoinPopulationByBaseId(Integer base_id);

    void updateStatusByBaseId(ApplicationVolunteer vo);

    void createVolunteerForm(VolunteerForm volunteerForm);

    List<VolunteerInfo> queryAllVolunteer();

    void delApplicatVolunteerByUserId(UserIdVo vo);

    void delApplicatFormByUserId(UserIdVo vo);

    List<VolunteerForm> queryVolunteerFormByUserId(UserIdVo vo);

    void updateAVStatusByid(ApplicationVo applicationVo);

    void updateAVPassTimeByid(ApplicationVo applicationVo);

    List<AvFormVo> queryAvFormByStatus(AvStatusVo vo);

    void changeVolunteerInfoByBase_id(VolunteerInfoVo vo);

    void createVolunteerInfo(VolunteerInfo po);

    void delVolunteerInfoByid(ViIdVo vo);

    List<VolunteerInfo> queryMVolunteerInfoByBaseId(BaseIdVo vo);


}
