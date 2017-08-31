package com.example.todo1.dao;

import com.example.todo1.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/8/18.
 */
@Repository
public class TodoDaoImpl implements TodoDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> get() {
        return jdbcTemplate.queryForList("SELECT * FROM todo");
    }

    @Override
    @Transactional
    public List<Map<String, Object>> insert(Todo todo) {
        jdbcTemplate.update("INSERT INTO todo(title) VALUES (?)", new Object[]{todo.getTitle()});
        //返回id
        return jdbcTemplate.queryForList("SELECT id FROM todo ORDER BY id DESC LIMIT 1");
    }

    @Override
    public int update(int id, Todo todo) {
        return jdbcTemplate.update("UPDATE todo SET title=? WHERE id=?", new Object[]{todo.getTitle(), id});
    }

    //更新完成状态
    @Override
    public int updateCom(int id, Todo todo) {
        return jdbcTemplate.update("UPDATE todo SET completed=? WHERE id = ?", new Object[]{todo.getCompleted(), id});
    }

    //更新所有完成状态
    @Override
    public int updateAllCom(Todo todo) {
        return jdbcTemplate.update("UPDATE todo SET completed=? WHERE id>0", new Object[]{todo.getCompleted()});
    }

    //删除单个事项
    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM todo WHERE id=?", id);
    }

    //删除所有已完成事项
    @Override
    public int deleteAllCom() {
        return  jdbcTemplate.update("DELETE FROM todo WHERE completed = 1");
    }

}