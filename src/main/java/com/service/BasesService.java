package com.service;

import com.pojo.BaseMessages;
import com.pojo.Bases;
import com.pojo.BasesImages;
import com.pojo.vo.BaseIdVo;
import com.pojo.vo.BaseMessageIdVo;
import com.pojo.vo.BasesVo;
import com.pojo.vo.QueryBasesVo;

import java.util.List;

public interface BasesService {
    Object queryBases();
    List<Bases> fuzzyQueryBases(QueryBasesVo bases);
    void delBases(BaseIdVo baseIdVo);
    Bases queryBasesById(Integer base_id);
    List<BasesImages> queryBasesImagesById(BaseIdVo baseIdVo);
    List<BaseMessages> queryBaseMessages(BaseIdVo baseIdVo);
    void changeBasesById(BasesVo vo);
    void changeBasesMessagesById(BaseMessages po);
    void insertBasesMessage(BaseMessages po);
    void delBasesMessageByBmId(BaseMessageIdVo vo);
    void changeBasesImageByBiId(BasesImages po);
    void changeBasesIconByBaseId(BasesVo vo);
}
