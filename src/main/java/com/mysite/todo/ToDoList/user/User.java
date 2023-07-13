package com.mysite.todo.ToDoList.user;

import com.mysite.todo.ToDoList.common.Time;
import com.mysite.todo.ToDoList.todo.TODO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "User_tb")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    //유저이름
    private String user_name;
    //유저이메일
    private String user_email;
    //유저의 할일 목록
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TODO> todoList = new ArrayList<>();

    public void modifyName(String name){
        this.user_name = name;
    }

}
