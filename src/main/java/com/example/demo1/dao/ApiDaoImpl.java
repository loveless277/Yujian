package com.example.demo1.dao;

import com.example.demo1.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/7/20.
 */
@Repository
public class ApiDaoImpl implements ApiDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Map<String,Object>> get() {
/*        return jdbcTemplate.query("select * from yujian", new RowMapper<Todo>() {
            @Override
            public Todo mapRow(ResultSet resultSet, int i) throws SQLException {
                Todo todo = new Todo();
                todo.setId(resultSet.getInt("id"));
                todo.setContent(resultSet.getString("content"));
                todo.setTime(resultSet.getTime("time"));
                todo.setTitle(resultSet.getString("title"));
                todo.setYxbz(resultSet.getByte("yxbz"));
                return todo;
            }
        });*/
        return jdbcTemplate.queryForList("SELECT * FROM yujian");
    }


    @Override
    public int insert(Todo todo) {
       // todo.setTime(new Date());
        return jdbcTemplate.update("INSERT INTO yujian (title,content) VALUES (?,?)",new Object[]{todo.getTitle(),todo.getContent()});
    }

    @Override
    public int update(int id, Todo todo) {

        return jdbcTemplate.update("UPDATE yujian SET title =?,content =?,yxbz =? WHERE id=?",new Object[]{todo.getTitle(),todo.getContent(),todo.getYxbz(),id});
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("UPDATE yujian SET yxbz=? WHERE id=?",0,id);
    }
}
