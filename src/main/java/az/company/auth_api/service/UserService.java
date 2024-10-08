package az.company.auth_api.service;

import az.company.auth_api.dto.UserDto;
import az.company.auth_api.entity.User;
import az.company.auth_api.exception.NotFoundException;
import az.company.auth_api.exception.UserExceptionCodes;
import az.company.auth_api.mapper.UserMapper;
import az.company.auth_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public UserDto getById(Long id) {
       User existingUser = userRepository
               .findById(id)
               .orElseThrow(() -> new NotFoundException(UserExceptionCodes.USER_NOT_FOUND));
       return userMapper.toDto(existingUser);
    }
}
