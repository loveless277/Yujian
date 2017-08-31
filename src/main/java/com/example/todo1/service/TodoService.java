package com.example.todo1.service;

import com.example.todo1.dao.TodoDao;
import com.example.todo1.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/8/18.
 */

@Service
public class TodoService {
    @Autowired
    private TodoDao todoDao;

    public List<Map<String,Object>> getTodo(){
        return todoDao.get();
    }

    public List<Map<String, Object>> insertTodo(Todo body){
        return todoDao.insert(body);
    }

    public int updateTodo(int id,Todo body){
        return todoDao.update(id, body);
    }

    //更新单个状态
    public int updateTodoCom(int id,Todo body){
        return todoDao.updateCom(id,body);
    }

    //更新所有状态
    public int updateAllCom(Todo body){
        return todoDao.updateAllCom(body);
    }

    //删除单个事项
    public int deleteTodo(int id){
        return todoDao.delete(id);
    }

    //删除所有已完成事项
    public int deleteAllCom(){
        return todoDao.deleteAllCom();
    }
}
