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


    @ApiResponses({
            @ApiResponse(responseCode = "200", description ="OK!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR!")
    })
    @Operation(summary = "투두 생성", description = "todo(할 일)의 생성 기능을 담당함. TodoDto를 body로 씀. userid와 todoDto(투두리스트 요구사항)을 파라미터로 넣으면 userid의 투두리스트에 투두가 추가됨.")
    //할 일(투두) 생성
    @PostMapping()
    public ResponseEntity<String> write(@RequestBody TodoDto todoDto, long userid){
        todoService.save(userid, todoDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("생성완료");
    }
    @Operation(summary = "투두 삭제", description = "todo(할 일) 들의 삭제기능을 담당함. 삭제하고싶은 Todo의 id를 파라미터로 넣으면 그 id를 가진 Todo가 삭제됨.")
    //할 일(투두) 삭제
    @DeleteMapping("/deleteTodo")
    public ResponseEntity<String> deleteTodo(@RequestParam long TodoId){
        todoService.deleteTodo(TodoId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("정상적으로 글이 삭제되었습니다.");
    }

    //유저의 할 일(투두) 전체조회 (제목이랑 기한만)
    @Operation(summary = "투두 전체조회", description = "userid를 파라미터로 넣으면 그 유저가 가지고 있는 모든 투두의 제목/시작날/마감날/중요여부를 반환함.")
    @GetMapping("/all/{userid}")
    public ResponseEntity<List<ShortTodoDto>> getAllTodos(@PathVariable long userid) {
        List<ShortTodoDto> todos = todoService.getAllTodos(userid);
        return ResponseEntity.status(HttpStatus.OK)
                .body(todos);
    }

    //유저의 중요한 일 조회
    @Operation(summary = "중요한 투두 조회", description = "userid를 파라미터로 넣으면 todo(할 일) 중 중요한 일 여부(isImportant)가 true인 투두의 제목/시작날/마감날/중요여부를 리스트로 반환함.")
    @GetMapping("/important/{userid}")
    public ResponseEntity<List<ShortTodoDto>> getImportantTodos(@PathVariable long userid){
        List<ShortTodoDto> todos = todoService.getImportantTodos(userid);
        return ResponseEntity.status(HttpStatus.OK)
                .body(todos);
    }

    //할 일 수정하기
    @Operation(summary = "투두 수정하기", description = "todo(할 일) 들의 수정 기능을 담당함. 수정하고 싶은 투두의 아이디를 넣고 TodoDto 양식에서 수정하고싶은 부분을 수정하고 반환하면 투두가 수정된 양식대로 수정됨.")
    @PutMapping("{todoId}")
    public ResponseEntity<String> EditTodo(@RequestBody TodoDto todoDto,@PathVariable long todoId) {
        todoService.edit(todoId, todoDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body("수정완료!");
    }
    //오늘 할 일 출력
    @Operation(summary = "오늘 할 일 출력", description = "userid를 파라미터로 넣으면 그 userid가 가지고 있는 투두 중 오늘의 날짜가 시작날과 마감날 사이에 있는 투두를 리스트로 만들어서 투두제목/투두시작날/투두 마감날/중요여부 를 리스트로 반환함.")
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

        return ResponseEntity.status(HttpStatus.OK)
                .body(todayTodos);

    }

    //할 일 검색(제목)
    @Operation(summary = "투두 검색(제목)", description = "검색하고 싶은 내용과 유저의 아이디를 파라미터로 넣으면 그 유저가 가지고 있는 투두의 제목에서 검색하고 싶은 내용을 포함하고있는 투두를 리스트로 만들어서 반환함.")
    @GetMapping ("{userid}/search/title/{keyword}")
    public ResponseEntity<List<ShortTodoDto>> getTitleSearch(@PathVariable long userid, @PathVariable String keyword) {
        List<ShortTodoDto> todos = todoService.getUsersAllTodos(userid);
        List<ShortTodoDto> matchingTodos = todos.stream()
                .filter(todo -> todo.getTodoTitle().contains(keyword))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(matchingTodos);
    }
    //할 일 검색(설명)
    @Operation(summary = "투두 검색(투두 설명)", description = "검색하고 싶은 내용과 유저의 아이디를 파라미터로 넣으면 그 유저가 가지고 있는 투두의 설명(description)에서 검색하고 싶은 내용을 포함하고있는 투두를 리스트로 만들어서 반환함.")
    @GetMapping("{userid}/search/Description/{keyword}")
    public ResponseEntity<List<ShortTodoDto>> getDescriptionSearch(@PathVariable long userid, @PathVariable String keyword) {
        List<ShortTodoDto> todos = todoService.getUsersAllTodos(userid);
        List<ShortTodoDto> matchingTodos = todos.stream()
                .filter(todo -> todo.getTodoTitle().contains(keyword))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(matchingTodos);
    }
}
