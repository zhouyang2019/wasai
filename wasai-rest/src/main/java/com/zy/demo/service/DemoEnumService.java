package com.zy.demo.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zy.demo.entity.DemoEnumEntity;
import com.zy.demo.mapper.DemoEnumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoEnumService {

    @Autowired
    private DemoEnumMapper demoEnumMapper;

    public DemoEnumEntity getByName(String name) {
        return demoEnumMapper.selectOne(Wrappers.<DemoEnumEntity>lambdaQuery()
                .eq(DemoEnumEntity::getName, name));
    }

    public int insert(DemoEnumEntity entity) {
        return demoEnumMapper.insert(entity);
    }

    public int updateNameById(Long id, String name) {
        DemoEnumEntity updateEntity = new DemoEnumEntity()
                .setId(id)
                .setName(name);
        return demoEnumMapper.updateById(updateEntity);
    }

}
