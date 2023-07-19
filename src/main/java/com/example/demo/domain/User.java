package com.example.demo.domain;

import javax.persistence.*;

import com.example.demo.common.Time;
import com.example.demo.dto.UserDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "User_tb")
@Entity
@Getter
@Builder
//없는생성자
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    //유저이름
    @Column(nullable = false)
    private String username;
    //유저이메일
    @Column(nullable = false)
    private String userEmail;
    //유저의 할일 목록

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Todo> todoList = new ArrayList<>();


    public void EditUser(UserDto userDto){
        this.username = userDto.getUsername();
        this.userEmail = userDto.getUserEmail();
    }

}
