package com.service.impl;

import com.common.utils.RedisUtil;
import com.common.utils.TimeUtils;
import com.mapper.PopularizationsMapper;
import com.pojo.Popularizations;
import com.service.PopularizationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopularizationsServiceImpl implements PopularizationsService {

    @Autowired
    private PopularizationsMapper mapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Popularizations> queryPopularizations(Popularizations popularizations) {
        return mapper.queryPopularizationsList(popularizations);
    }

    @Override
    public void createPopularization(Popularizations popularizations) {
        popularizations.setP_createtime(TimeUtils.getNowTime());
        popularizations.setP_updatetime(TimeUtils.getNowTime());
        popularizations.setP_pv(1);
        mapper.createPopularization(popularizations);
        redisUtil.set("allPopularizations", mapper.queryPopularizationsList(popularizations));
    }

    @Override
    public List<Popularizations> fuzzyQueryPopularizations(Popularizations popularizations) {
        return mapper.fuzzyQueryPopularizations(popularizations);
    }
}
