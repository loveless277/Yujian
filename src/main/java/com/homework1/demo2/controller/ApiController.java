package com.homework1.demo2.controller;

import com.homework1.demo2.entity.ResponseMessage;
import com.homework1.demo2.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/7/24.
 */
@RestController
public class ApiController {
    @Autowired
    private ApiService apiService;

    @GetMapping(value = "/api/users/{userid}/menu")
    public ResponseEntity<?> getUserMenu(@PathVariable int userid){
        List<Map<String,Object>> UserMenu = apiService.getUserMenu(userid);
        ResponseMessage rm = new ResponseMessage("200","",UserMenu);
        return new ResponseEntity<>(rm, HttpStatus.OK);
    }
    @GetMapping(value = "/api/users/{userid}")
    public ResponseEntity<?> getUserAll(@PathVariable int userid){
        List<Map<String,Object>> UserAll = apiService.getUserAll(userid);
        ResponseMessage rm = new ResponseMessage("200","",UserAll);
        return new ResponseEntity<>(rm,HttpStatus.OK);
    }

    @PostMapping(value = "/api/relation/rolemenu")
    public ResponseEntity<?> insertRoleMenu( int rid, int mid){
        apiService.updateRoleMenu(rid, mid);
        ResponseMessage rm =new ResponseMessage("201","","更新成功");
        return new ResponseEntity<>(rm,HttpStatus.CREATED);
    }

    @PostMapping(value = "/api/relation/roleuser")
    public ResponseEntity<?> insertUserRole(int uid,int rid){
        apiService.updateUserRole(uid, rid);
        ResponseMessage rm= new ResponseMessage("201","","更新成功");
        return new ResponseEntity<>(rm,HttpStatus.CREATED);
    }
}
