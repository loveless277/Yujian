package com.homework1.demo2.controller;

import com.homework1.demo2.entity.Menu;
import com.homework1.demo2.entity.ResponseMessage;
import com.homework1.demo2.service.MenuService;
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
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/menus")
    public ResponseEntity<?> getMenu() {
        List<Map<String, Object>> menu = menuService.getMenu();
        ResponseMessage rm = new ResponseMessage("200","",menu);
        return new ResponseEntity<>(rm, HttpStatus.OK);
    }

    @PostMapping(value = "/menus")
    public ResponseEntity<?> insertMenu(@RequestBody Menu body){
        menuService.insertMenu(body);
        ResponseMessage rm = new ResponseMessage("201","","添加成功");
        return new  ResponseEntity<>(rm,HttpStatus.CREATED);
    }

    @PutMapping(value = "/menus/{id}")
    public ResponseEntity<?> updateMenu(@PathVariable int id,@RequestBody Menu body){
        menuService.updateMenu(id, body);
        ResponseMessage rm =new ResponseMessage("202","","修改成功");
        return new ResponseEntity<>(rm,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/menus/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable  int id){
        menuService.deleteMenu(id);
        ResponseMessage rm = new ResponseMessage("203", "", "删除成功");
        return new ResponseEntity<>(rm, HttpStatus.CREATED);

    }
}
