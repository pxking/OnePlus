package com.example.demo.mapper;

import com.example.demo.entity.District;

public interface DistrictMapper {
    int insert(District record);

    int insertSelective(District record);
}