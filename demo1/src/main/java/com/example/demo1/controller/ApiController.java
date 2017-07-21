package com.example.demo1.controller;

import com.example.demo1.entity.ResponseMessage;
import com.example.demo1.entity.Todo;
import com.example.demo1.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created by acerpc on 2017/7/19.
 */
@RestController
public class ApiController {
    @Autowired
    private ApiService apiService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping(value = "/api")
    public ResponseEntity<?> getApi(){
        List<Map<String,Object>> todos = apiService.getTodo();

        ResponseMessage rm = new ResponseMessage("200","",todos);
        return new ResponseEntity<>(rm, HttpStatus.OK);
    }

    @GetMapping(value = "/api/{id}")
    public ResponseEntity<?> getApiWithParam(@PathVariable String id) {
        Map<String,Object> map = jdbcTemplate.queryForMap("SELECT * FROM yujian WHERE id=?",id);
        ResponseMessage rm = new ResponseMessage("200","",map);
        return new ResponseEntity<>(rm,HttpStatus.OK);
    }

    @PostMapping(value = "/api")
    public ResponseEntity<?> newApi(@RequestBody Todo body){
      //  Map<String,Object> result = apiService.newApi(body);
        apiService.insertTodo(body);
        ResponseMessage rm = new ResponseMessage("201","","success");
        return new  ResponseEntity<>(rm,HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/{id}")
    public ResponseEntity<?> updateApi(@PathVariable int id,@RequestBody Todo body){

        apiService.updateTodo(id,body);
        ResponseMessage rm = new ResponseMessage("200","","success");
        return new  ResponseEntity<>(rm,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/api/{id}")
    public ResponseEntity<?> Api(@PathVariable int id) {

       apiService.deleteTodo(id);
        ResponseMessage rm = new ResponseMessage("200","","success");
        return new  ResponseEntity<>(rm,HttpStatus.CREATED);
    }
/**    @RequestMapping("/api")*/

}
