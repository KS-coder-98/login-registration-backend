package com.example.demo.mapper;

import com.example.demo.dto.AppUserAuthenticationLogDto;
import com.example.demo.model.appuser.AppUserAuthenticationLog;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppUserAuthenticationLogMapper {

    AppUserAuthenticationLogDto map(AppUserAuthenticationLog log);

    List<AppUserAuthenticationLogDto> mapToList(List<AppUserAuthenticationLog> list);
}
