package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;

    //모든 유저 표시
    public List<UserRequestDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserRequestDto> userInfoDtos =  new ArrayList<>();
        for(User user : allUsers ){
           UserRequestDto userInfoDto =  new UserRequestDto(user.getUserid(),user.getUsername());
           userInfoDtos.add(userInfoDto);
        }
        return userInfoDtos;
    }

    public List<User> getAllUserinfo(){
        return userRepository.findAll();
    }


//    //유저 회원가입
//    public void Register(UserDto userDto){
//        User user = userDto.toEntity(userDto);
//        userRepository.save(user);
//    }

    //유저 수정
    public void editUser(Long userid, UserDto userDto){
        User user = userRepository.findByUserid(userid)
                .orElseThrow(() -> new RuntimeException());
        user.EditUser(userDto);
        userRepository.save(user);
    }

    public void Withdrawal(Long userid){
        User user = userRepository.findByUserid(userid)
                .orElseThrow(() -> new RuntimeException());
        userRepository.delete(user);
    }


}

