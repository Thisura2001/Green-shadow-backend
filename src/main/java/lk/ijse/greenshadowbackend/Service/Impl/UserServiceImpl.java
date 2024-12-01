package lk.ijse.greenshadowbackend.Service.Impl;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.Impl.UserDto;
import lk.ijse.greenshadowbackend.Dto.UserStatus;
import lk.ijse.greenshadowbackend.Entity.Impl.UserEntity;
import lk.ijse.greenshadowbackend.Exception.UserNotFoundException;
import lk.ijse.greenshadowbackend.Repository.UserRepo;
import lk.ijse.greenshadowbackend.Service.UserService;
import lk.ijse.greenshadowbackend.Util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        return null;
    }

    @Override
    public void updateUser(UserDto UserDto, String userId) {
        Optional<UserEntity> byId = userRepo.findById(userId);
        if (!byId.isPresent()){
            throw new UserNotFoundException("User not found");
        }else {
            byId.get().setPassword(UserDto.getPassword());
            byId.get().setRole(UserDto.getRole());
        }
    }

    @Override
    public void deleteUser(String userId) {
    }

    @Override
    public UserDetailsService userDetailsService() {
        return null;
    }
}
