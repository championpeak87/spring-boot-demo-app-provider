package com.example.demo.util;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.example.demo.model.User;
import com.example.demo.model.db.TBUser;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IObjectMapper {
    User convToApi(TBUser tbUser);

    TBUser convToTb(User user);
}
