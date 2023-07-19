package com.example.demo.controller;

import com.example.demo.domain.Todo;
import com.example.demo.domain.User;
import com.example.demo.dto.ShortTodoDto;
import com.example.demo.dto.TodoDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/Todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @Operation(summary = "todo api", description = "todo(할 일) 들의 생성/삭제/수정/검색 기능을 담당함.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description ="OK!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR!")
    })
    //할 일(투두) 생성
    @PostMapping()
    public ResponseEntity<String> write(@RequestBody TodoDto todoDto, long userid){
        todoService.save(userid, todoDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("생성완료");
    }
    //할 일(투두) 삭제
    @DeleteMapping("/deleteTodo")
    public ResponseEntity<String> deleteTodo(@RequestParam long TodoId){
        todoService.deleteTodo(TodoId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("정상적으로 글이 삭제되었습니다.");
    }

    //유저의 할 일(투두) 전체조회 (제목이랑 기한만)
    @GetMapping("/all/{userid}")
    public ResponseEntity<List<ShortTodoDto>> getAllTodos(@PathVariable long userid) {
        List<ShortTodoDto> todos = todoService.getAllTodos(userid);
        return ResponseEntity.status(HttpStatus.OK)
                .body(todos);
    }

    //유저의 중요한 일 조회
    @GetMapping("/important/{userid}")
    public ResponseEntity<List<ShortTodoDto>> getImportantTodos(@PathVariable long userid){
        List<ShortTodoDto> todos = todoService.getImportantTodos(userid);
        return ResponseEntity.ok(todos);
    }

    //할 일 수정하기
    @PutMapping("{todoId}")
    public ResponseEntity<String> EditTodo(@RequestBody TodoDto todoDto,@PathVariable long todoId) {
        todoService.edit(todoId, todoDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body("수정완료!");
    }
    //오늘 할 일 출력
    @GetMapping("today/{userid}")
    public ResponseEntity<List<ShortTodoDto>> getTodayTodos(@PathVariable long userid){
        List<ShortTodoDto> todos = todoService.getAllTodos(userid);

        LocalDate today = LocalDate.now();

        List<ShortTodoDto> todayTodos = todos.stream()
                .filter(todo -> {
                    LocalDate startDate = todo.getStartDate().toLocalDate();
                    LocalDate deadDate = todo.getDeadDate().toLocalDate();
                    return !startDate.isAfter(today) && !deadDate.isBefore(today);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(todayTodos);

    }

    //할 일 검색(제목)
    @GetMapping ("search/title")
    public ResponseEntity<List<Todo>> getTitleSearch(@RequestParam("keyword") String keyword) {
        List<Todo> todos = todoService.getUsersAllTodos();

        List<Todo> matchingTodos = todos.stream()
                .filter(todo -> todo.getTodoTitle().contains(keyword))
                .collect(Collectors.toList());

        return ResponseEntity.ok(matchingTodos);
    }
    //할 일 검색(설명)
    @GetMapping("search/Description")
    public ResponseEntity<List<Todo>> getDescriptionSearch(@RequestParam("keyword") String keyword) {
        List<Todo> todos = todoService.getUsersAllTodos();

        List<Todo> matchingTodos = todos.stream()
                .filter(todo -> todo.getTodoDescription().contains(keyword))
                .collect(Collectors.toList());

        return ResponseEntity.ok(matchingTodos);
    }
}
