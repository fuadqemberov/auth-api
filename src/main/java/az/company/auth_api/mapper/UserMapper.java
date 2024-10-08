package az.company.auth_api.mapper;

import az.company.auth_api.dto.UserDto;
import az.company.auth_api.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
}
