package com.mysite.todo.ToDoList.controller;

import com.mysite.todo.ToDoList.DTO.TodoDto;
import com.mysite.todo.ToDoList.service.TODOService;
import com.mysite.todo.ToDoList.todo.TODO;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TODO")
@RequiredArgsConstructor
public class TODOController {
    private final TODOService todoService;

    //투두 만들기
    public void create(Long userid, @RequestBody TodoDto todoDto) {
        todoService.write(userid, todoDto);
    }
    //투두 출력
    @GetMapping("")
    public String showALlTodo(Model model, @PathVariable Long userId) {
        List<TODO> todoList = todoService.getTodosByUserId(userId);
        model.addAttribute("todoList", todoList);
        return "todoList"; // 템플릿의 이름 반환. html에서 출력
    }
    //투두 삭제
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);

    }


    //중요한 할일 출력
    //@GetMapping("/users/{userId}/important-todos")
    //public String getImportantTodos(Model model, @PathVariable Long userId) {
    //    List<TODO> importantTodos = todoService.getImportantTodosByUserId(userId);
    //    model.addAttribute("importantTodos", importantTodos);
    //    return "importantTodos"; // 템플릿의 이름 반환
    //}
    //수정하기
    @PutMapping("/{user_id}/{todo_id}")
    public void updateTodo(@PathVariable Long user_id, @PathVariable Long todo_id, @RequestBody TodoDto todoDto) {
        todoService.edit(user_id, todo_id, todoDto);
    }

    //@GetMapping("/{title}")
    //public String TODOSearchTitleList(@PathVariable String title){
    //    return "==========Search result=========";
    //}

    //@GetMapping("/{Explanation}")
    //public String TODOSearchExplanationList(@PathVariable String Explanation){
    //    return "==========Search result=========";
    //}

    //@GetMapping("/{word}")
    //public String TODOSearchAllList(@PathVariable String word){
    //    return "==========Search result=========";
    //}

}
