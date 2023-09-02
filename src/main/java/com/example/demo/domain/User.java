package com.example.demo.domain;

import javax.persistence.*;

import com.example.demo.common.Time;
import com.example.demo.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "User_tb")
@Entity
@Getter
@Builder
//매개변수가 없는생성자
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends Time implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

    //유저이름
    @Column(nullable = false, unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    //유저이메일
    @Column(nullable = false)
    private String userEmail;
    //유저의 할일 목록

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Todo> todoList = new ArrayList<>();

    @Builder.Default
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(nullable = false)
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    //userDetails 구성요소
    @Override
    public String getUsername() {  // 계정의 이름을 리턴, 일반적으로 id
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {  // 계정이 만료되었는지 리턴, true는 만료 되지 않음.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {  // 계정이 잠겨있는지 여부
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {   // 비밀번호가 만료되었는지 여부
        return true;
    }

    @Override
    public boolean isEnabled() {    // 계정이 활성회 되어있는지 여부
        return true;
    }

    public void EditUser(UserDto userDto){
        this.username = userDto.getUsername();
        this.userEmail = userDto.getUserEmail();
    }

    public Long getUserid(){
        return userid;
    }


}
