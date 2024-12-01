package lk.ijse.greenshadowbackend.Service.Impl;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.Impl.UserDto;
import lk.ijse.greenshadowbackend.Dto.UserStatus;
import lk.ijse.greenshadowbackend.Entity.Impl.UserEntity;
import lk.ijse.greenshadowbackend.Repository.UserRepo;
import lk.ijse.greenshadowbackend.Service.UserService;
import lk.ijse.greenshadowbackend.Util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private Mapping mapping;
    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public UserStatus getUser(String userId) {
        if (userRepo.existsById(userId)){
            UserEntity userEntity = userRepo.getReferenceById(userId);
            return mapping.toUserDTO(userEntity);
        }else {
            return (UserStatus) new SelectedErrorStatusCode(2,"Invalid User Id");
        }
    }

    @Override
    public void updateUser(UserDto UserDto, String userId) {

    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public UserDetailsService userDetailsService() {
        return null;
    }
}
