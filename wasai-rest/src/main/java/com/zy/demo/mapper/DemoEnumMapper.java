package com.zy.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.demo.entity.DemoEnumEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DemoEnumMapper extends BaseMapper<DemoEnumEntity> {
}
