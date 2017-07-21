package com.example.demo1.dao;

import com.example.demo1.entity.Todo;

import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/7/20.
 */
public interface ApiDao {
  List<Map<String,Object>> get();

  int insert(Todo todo);

  int update(int id,Todo todo);

  int delete(int id);


}
