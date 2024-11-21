//package lk.ijse.greenshadowbackend.Service.Impl;
//
//import lk.ijse.greenshadowbackend.Dto.Impl.UserDto;
//import lk.ijse.greenshadowbackend.Entity.Impl.UserEntity;
//import lk.ijse.greenshadowbackend.Repository.UserRepo;
//import lk.ijse.greenshadowbackend.Secure.JWTAuthResponse;
//import lk.ijse.greenshadowbackend.Secure.SignIn;
//import lk.ijse.greenshadowbackend.Service.AuthService;
//import lk.ijse.greenshadowbackend.Service.JWTService;
//import lk.ijse.greenshadowbackend.Util.Mapping;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor //constructor injection
//public class AuthServiceIMPL implements AuthService {
//    private final UserRepo userRepo;
//    private final Mapping mapping;
//    private final JWTService jwtService;
//    private final AuthenticationManager authenticationManager;
//    @Override
//    public JWTAuthResponse signIn(SignIn signIn) {
//       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword()));
//       var user = userRepo.findByEmail(signIn.getEmail()).
//               orElseThrow(() -> new RuntimeException("User not found"));
//
//        String generateToken = jwtService.generateToken(user);
//        return JWTAuthResponse.builder().token(generateToken).build();
//
//    }
//
//    @Override
//    public JWTAuthResponse signUp(UserDto userDTO) { //save user
//        UserEntity userEntity = mapping.toUserEntity(userDTO);
//        userRepo.save(userEntity);
//         //generate token and return
//        String generateToken = jwtService.generateToken(userEntity);
//        return JWTAuthResponse.builder().token(generateToken).build();
//    }
//
//    @Override
//    public JWTAuthResponse refreshToken(String accessToken) {
//        //extract userName(Email)
//        String userName = jwtService.extractUserName(accessToken);
//        //is exist the user in the DB
//        UserEntity FindUser = userRepo.findByEmail(userName).orElseThrow(() -> new RuntimeException("User Not Found !!"));
//        String refreshToken = jwtService.refreshToken(FindUser);
//        return JWTAuthResponse.builder().token(refreshToken).build();
//
//    }
//}