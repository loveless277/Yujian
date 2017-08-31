package com.example.todo1.controller;

import com.example.todo1.entity.ResponseMessage;
import com.example.todo1.entity.Todo;
import com.example.todo1.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/8/18.
 */

@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping(value = "/todos")
    public ResponseEntity<?> getTodos(){
        List<Map<String,Object>> todos = todoService.getTodo();
        ResponseMessage rm= new ResponseMessage("200","",todos);
        return new ResponseEntity<>(rm, HttpStatus.OK);
    }

    @PostMapping(value = "/todos")
    public ResponseEntity<?> newTodos(@RequestBody Todo body){
        List<Map<String,Object>> todos = todoService.insertTodo(body);
        ResponseMessage rm= new ResponseMessage("200","",todos);
        return new ResponseEntity<>(rm, HttpStatus.CREATED);
    }

    //更新事项
    @PutMapping(value = "/todos/{id}")
    public ResponseEntity updateTodos(@PathVariable int id,@RequestBody Todo body){
        todoService.updateTodo(id, body);
        ResponseMessage rm = new ResponseMessage("200","","success");
        return new  ResponseEntity<>(rm,HttpStatus.CREATED);
    }

    //更新单个事项状态
    @PutMapping(value = "/todos/{id}/com")
    public ResponseEntity updateTodosCom(@PathVariable int id,@RequestBody Todo body){
        todoService.updateTodoCom(id, body);
        ResponseMessage rm = new ResponseMessage("200","","success");
        return new ResponseEntity<>(rm,HttpStatus.CREATED);
    }

    //更新所有事项状态
    @PutMapping(value = "/todos/allcom")
    public ResponseEntity updateTodosALLCom(@RequestBody Todo body){
        todoService.updateAllCom(body);
        ResponseMessage rm = new ResponseMessage("200","","success");
        return new ResponseEntity<>(rm,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/todos/{id}")
    public ResponseEntity deleteTodos(@PathVariable int id){
        todoService.deleteTodo(id);
        ResponseMessage rm = new ResponseMessage("200","","success");
        return new  ResponseEntity<>(rm,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/todos/deletecom")
    public ResponseEntity deleteAllCom(){
        todoService.deleteAllCom();
        ResponseMessage rm = new ResponseMessage("200","","success");
        return new  ResponseEntity<>(rm,HttpStatus.CREATED);
    }
}
