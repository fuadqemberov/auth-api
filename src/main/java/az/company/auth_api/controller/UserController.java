package az.company.auth_api.controller;

import az.company.auth_api.dto.UserDto;
import az.company.auth_api.entity.User;
import az.company.auth_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> allUsers() {
        return ResponseEntity.ok(userService.allUsers());
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id ){
        return userService.getById(id);
    }


}
