package lk.ijse.greenshadowbackend.Controller;

import lk.ijse.greenshadowbackend.CustomStatus.ErrorStatus;
import lk.ijse.greenshadowbackend.Dto.Impl.UserDto;
import lk.ijse.greenshadowbackend.Dto.UserStatus;
import lk.ijse.greenshadowbackend.Exception.UserNotFoundException;
import lk.ijse.greenshadowbackend.Secure.JWTAuthResponse;
import lk.ijse.greenshadowbackend.Service.UserService;
import lk.ijse.greenshadowbackend.Util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping(value ="/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> updateUser(@PathVariable String userId, @RequestBody UserDto userDto) {
        try {
            if (!Regex.idValidator(userId).matches() || userDto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            userService.updateUser(userDto, userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
