package com.example.todo1.dao;

import com.example.todo1.entity.Todo;

import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/8/18.
 */
public interface TodoDao {
    List<Map<String,Object>> get();

    List<Map<String, Object>> insert(Todo todo);

    int update(int id,Todo todo);

    //更新单个状态
    int updateCom(int id,Todo todo);

    //更新所有状态
    int updateAllCom(Todo todo);

    int delete(int id);

    //删除所有已完成事项
    int deleteAllCom();
}
