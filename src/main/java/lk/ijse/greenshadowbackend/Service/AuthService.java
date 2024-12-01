package lk.ijse.greenshadowbackend.Service;

import lk.ijse.greenshadowbackend.Dto.Impl.UserDto;
import lk.ijse.greenshadowbackend.Secure.JWTAuthResponse;
import lk.ijse.greenshadowbackend.Secure.SignIn;

public interface AuthService {
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse signUp(UserDto userDTO);
    JWTAuthResponse refreshToken(String accessToken);
}
