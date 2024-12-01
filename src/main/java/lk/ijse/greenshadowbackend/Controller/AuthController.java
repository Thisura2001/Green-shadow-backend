package lk.ijse.greenshadowbackend.Controller;

import lk.ijse.greenshadowbackend.Dto.Impl.UserDto;
import lk.ijse.greenshadowbackend.Exception.DataPersistException;
import lk.ijse.greenshadowbackend.Secure.JWTAuthResponse;
import lk.ijse.greenshadowbackend.Secure.SignIn;
import lk.ijse.greenshadowbackend.Service.AuthService;
import lk.ijse.greenshadowbackend.Util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthService authService;
    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> saveUser(@RequestBody UserDto userDto) {
        try {
            userDto.setUser_id(AppUtil.generateUserId());
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            return ResponseEntity.ok(authService.signUp(userDto));
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("signin")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn signIn){
        return ResponseEntity.ok(authService.signIn(signIn));
    }
}
