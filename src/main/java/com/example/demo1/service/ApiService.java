package com.example.demo1.service;

import com.example.demo1.dao.ApiDao;
import com.example.demo1.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by acerpc on 2017/7/19.
 */
@Service
public class ApiService {
    @Autowired
    private ApiDao apiDao;

    public List<Map<String,Object>> getTodo(){
        return apiDao.get();
    }
    public int insertTodo(Todo body){
        return apiDao.insert(body);
    }

    public int deleteTodo(int id){
        return apiDao.delete(id);
    }

    public int updateTodo(int id,Todo body){
        return apiDao.update(id,body);
    }

  /*  public Map<String,Object> getApi(){
        Map<String,Object> map = new HashMap<>();
        map.put("message","get method");
        return map;
    }

    public Map<String ,Object> getApi(String id,String cata) {
        Map<String,Object> map = new HashMap<>();
        map.put("message","get method with param");
        map.put("pathvariable",id);
        map.put("requestparm",cata);
        return map;
    }

    public Map<String ,Object> newApi(Map<String ,Object> body){
        String username = "用户名是："+(String)body.get("username");
        Map<String,Object> map = new HashMap<>();
        map.put("message","post method");
        map.put("username",username);
        return map;
    }*/
}
