package com.company.portal.demo.mapper.user;

import com.company.portal.demo.entity.User;
import com.company.portal.demo.payload.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper {
    UserDto userToUserDto(User user);
    List<UserDto> usersToUserDtoList(List<User> users);
}
