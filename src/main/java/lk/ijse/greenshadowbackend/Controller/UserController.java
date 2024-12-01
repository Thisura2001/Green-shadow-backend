package lk.ijse.greenshadowbackend.Controller;

import lk.ijse.greenshadowbackend.CustomStatus.ErrorStatus;
import lk.ijse.greenshadowbackend.Dto.UserStatus;
import lk.ijse.greenshadowbackend.Service.UserService;
import lk.ijse.greenshadowbackend.Util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserStatus getUserById(@PathVariable ("userId") String userId) {

        if (!Regex.userIdMatcher(userId)) {
            return new ErrorStatus(1, "Invalid User Id");
        }
        return userService.getUser(userId);
    }
}
