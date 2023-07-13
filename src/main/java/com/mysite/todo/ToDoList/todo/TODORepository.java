package com.mysite.todo.ToDoList.todo;

import com.mysite.todo.ToDoList.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TODORepository extends JpaRepository<TODO, Long> {

    List<TODO> findByUser(User user);
    //List<TODO> findByUser_user_idAndImportant_status(Long userId, boolean importantStatus);
}
