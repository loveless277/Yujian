package com.homework1.demo2.controller;


import com.homework1.demo2.entity.Role;
import com.homework1.demo2.entity.ResponseMessage;
import com.homework1.demo2.service.RoleService;
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
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/roles")
    public ResponseEntity<?> getRole() {
        List<Map<String, Object>> role = roleService.getRole();
        ResponseMessage rm = new ResponseMessage("200","",role);
        return new ResponseEntity<>(rm, HttpStatus.OK);
    }

    @PostMapping(value = "/roles")
    public ResponseEntity<?> insertRole(@RequestBody Role body){
        roleService.insertRole(body);
        ResponseMessage rm = new ResponseMessage("201","","添加成功");
        return new  ResponseEntity<>(rm,HttpStatus.CREATED);
    }

    @PutMapping(value = "/roles/{id}")
    public ResponseEntity<?> updateRole(@PathVariable int id,@RequestBody Role body){
        roleService.updateRole(id, body);
        ResponseMessage rm =new ResponseMessage("202","","修改成功");
        return new ResponseEntity<>(rm,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/roles/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable  int id){
        roleService.deleteRole(id);
        ResponseMessage rm = new ResponseMessage("203", "", "删除成功");
        return new ResponseEntity<>(rm, HttpStatus.CREATED);

    }
}
