package com.homework1.demo2.controller;

import com.homework1.demo2.entity.ResponseMessage;
import com.homework1.demo2.entity.User;
import com.homework1.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/7/21.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<?> getUser() {
        List<Map<String, Object>> user = userService.getUser();
        ResponseMessage rm = new ResponseMessage("200","",user);
        return new ResponseEntity<>(rm, HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<?> insertUser(@RequestBody User body){
        userService.insertUser(body);
        ResponseMessage rm = new ResponseMessage("201","","添加成功");
        return new  ResponseEntity<>(rm,HttpStatus.CREATED);
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id,@RequestBody User body){
        userService.updateUser(id, body);
        ResponseMessage rm =new ResponseMessage("202","","修改成功");
        return new ResponseEntity<>(rm,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/users/{id}")
        public ResponseEntity<?> deleteUser(@PathVariable  int id){
            userService.deleteUser(id);
            ResponseMessage rm = new ResponseMessage("203", "", "删除成功");
            return new ResponseEntity<>(rm, HttpStatus.CREATED);

    }
}
