package com.example.demo.repository;

import com.example.demo.domain.Todo;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserUserid(Long userid);

    Optional<Todo> findByTodoId(Long TodoId);


    List<Todo> findByUserUseridAndIsImportant(long userid, Boolean IsImportant);

}
