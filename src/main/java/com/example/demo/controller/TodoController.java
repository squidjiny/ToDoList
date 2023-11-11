package com.example.demo.controller;

import com.example.demo.common.CommonResponse;
import com.example.demo.domain.Todo;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.ShortTodoDto;
import com.example.demo.dto.TodoDto;
import com.example.demo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "TodoController", description = "할 일 API")
@RestController
@RequestMapping("/todos")
public class TodoController {


    private final TodoService todoService;

    /*
    @RequireArgument, @AllArgumentConstructor, @NoArgumentConstructor 를 상황에 따라 쓸 수 있으나
    처음 해보는 단계이기 때문에 눈에 잘 보이는 형식으로 의존성을 주입한다.
    */
    @Autowired
    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @Operation(summary = "투두 생성", description = "todo(할 일)의 생성 기능을 담당함. TodoDto를 body로 씀. userid와 todoDto(투두리스트 요구사항)을 파라미터로 넣으면 userid의 투두리스트에 투두가 추가됨.")
    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "투두리스트 생성 성공", content = @Content(schema = @Schema(implementation = TodoDto.class))),
            @ApiResponse(responseCode = "400", description = "투두리스트 생성 실패"),
    })
    @io.swagger.annotations.ApiResponses(
            @io.swagger.annotations.ApiResponse(code = 201,message = "ok,",response =TodoDto.class)
    )
    public ResponseEntity<ResponseDto> write(@RequestBody TodoDto todoDto, Long userid){
        TodoDto saveTodo = todoService.save(userid, todoDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(CommonResponse.SUCCESS, saveTodo));
    }
    @Operation(summary = "투두 삭제", description = "todo(할 일) 들의 삭제기능을 담당함. 삭제하고싶은 Todo의 id를 파라미터로 넣으면 그 id를 가진 Todo가 삭제됨.")
    @DeleteMapping("/{TodoId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "투두리스트 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "삭제하려는 투두리스트가 없습니다"),
            @ApiResponse(responseCode = "403", description = "삭제 권한이 없습니다.")
    })

    @io.swagger.annotations.ApiResponses(
            @io.swagger.annotations.ApiResponse(code = 200, message = "ok", responseContainer = "1")
    )
    public ResponseEntity<ResponseDto> deleteTodo(@PathVariable Long TodoId){
        todoService.deleteTodo(TodoId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(CommonResponse.SUCCESS, TodoId));
    }



    //유저의 할 일(투두) 전체조회 (제목이랑 기한만)
    @Operation(summary = "투두 전체조회", description = "userid를 파라미터로 넣으면 그 유저가 가지고 있는 모든 투두의 제목/시작날/마감날/중요여부를 반환함.")
    @GetMapping("/all/{userid}")
    @ApiResponse(responseCode = "200", description = "전체 조회 성공")
    public ResponseEntity<ResponseDto> getAllTodos(@PathVariable Long userid) {
        List<ShortTodoDto> todos = todoService.getAllTodos(userid);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(CommonResponse.SUCCESS, todos));
    }

    //유저의 중요한 일 조회
    @Operation(summary = "중요한 투두 조회", description = "userid를 파라미터로 넣으면 todo(할 일) 중 중요한 일 여부(isImportant)가 true인 투두의 제목/시작날/마감날/중요여부를 리스트로 반환함.")
    @GetMapping("/important/{userid}")
    @ApiResponse(responseCode = "200", description = "전체 조회 성공")
    public ResponseEntity<ResponseDto> getImportantTodos(@PathVariable Long userid){
        List<ShortTodoDto> todos = todoService.getImportantTodos(userid);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(CommonResponse.SUCCESS, todos));
    }

    //할 일 수정하기
    @Operation(summary = "투두 수정하기", description = "todo(할 일) 들의 수정 기능을 담당함. 수정하고 싶은 투두의 아이디를 넣고 TodoDto 양식에서 수정하고싶은 부분을 수정하고 반환하면 투두가 수정된 양식대로 수정됨.")
    @PutMapping("/{todoId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "투두리스트 수정 성공"),
            @ApiResponse(responseCode = "404", description = "수정하려는 투두리스트가 없습니다."),
            @ApiResponse(responseCode = "403", description = "수정 권한이 없습니다.")
    })
    public ResponseEntity<ResponseDto> EditTodo(@RequestBody TodoDto todoDto,@PathVariable Long todoId) {
        todoService.edit(todoId, todoDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(CommonResponse.SUCCESS, todoDto));
    }
    //오늘 할 일 출력
    @Operation(summary = "오늘 할 일 출력", description = "userid를 파라미터로 넣으면 그 userid가 가지고 있는 투두 중 오늘의 날짜가 시작날과 마감날 사이에 있는 투두를 리스트로 만들어서 투두제목/투두시작날/투두 마감날/중요여부 를 리스트로 반환함.")
    @GetMapping("/today/{userid}")
    @ApiResponse(responseCode = "200", description = "오늘 할 일 조회 성공")
    public ResponseEntity<ResponseDto> getTodayTodos(@PathVariable Long userid){
        List<ShortTodoDto> todos = todoService.getAllTodos(userid);

        LocalDate today = LocalDate.now();

        List<ShortTodoDto> todayTodos = todos.stream()
                .filter(todo -> {
                    LocalDate startDate = todo.getStartDate();
                    LocalDate deadDate = todo.getDeadDate();
                    return !startDate.isAfter(today) && !deadDate.isBefore(today);
                })
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(CommonResponse.SUCCESS, todayTodos));

    }

    //할 일 검색(제목)
    @Operation(summary = "투두 검색(제목)", description = "검색하고 싶은 내용과 유저의 아이디를 파라미터로 넣으면 그 유저가 가지고 있는 투두의 제목에서 검색하고 싶은 내용을 포함하고있는 투두를 리스트로 만들어서 반환함.")
    @GetMapping ("/title/{userid}/{keyword}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "투두리스트 제목 조회 성공"),
            @ApiResponse(responseCode = "404", description = "찾으려는 투두리스트가 없습니다.")
    })
    public ResponseEntity<ResponseDto> getTitleSearch(@PathVariable Long userid, @PathVariable String keyword) {
        List<TodoDto> todos = todoService.getUsersAllTodos(userid);
        List<TodoDto> matchingTodos = todos.stream()
                .filter(todo -> todo.getTodoTitle().contains(keyword))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(CommonResponse.SUCCESS,matchingTodos));
    }
    //할 일 검색(설명)
    @Operation(summary = "투두 검색(투두 설명)", description = "검색하고 싶은 내용과 유저의 아이디를 파라미터로 넣으면 그 유저가 가지고 있는 투두의 설명(description)에서 검색하고 싶은 내용을 포함하고있는 투두를 리스트로 만들어서 반환함.")
    @GetMapping("/description/{userid}/{keyword}")
    public ResponseEntity<ResponseDto> getDescriptionSearch(@PathVariable Long userid, @PathVariable String keyword) {
        List<TodoDto> todos = todoService.getUsersAllTodos(userid);
        List<TodoDto> matchingTodos = todos
                .stream()
                .filter(todo -> todo.getTodoDescription().contains(keyword))
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CommonResponse.SUCCESS,matchingTodos));
    }

    @Operation(summary = "투두 finished 바꾸기", description = "투두 체크 제어용. isFinished = true에 이거 쓰면 false되고 isFinished =false에 이거 쓰면 true됨")
    @GetMapping("/{todoId}")
    public ResponseEntity<ResponseDto> todoReverseCheck(@PathVariable Long todoId) {
        todoService.reverseTodoFinished(todoId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CommonResponse.SUCCESS,"null"));
    }


}
