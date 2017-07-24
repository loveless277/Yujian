package com.homework1.demo2.dao.Impl;

import com.homework1.demo2.dao.UserDao;
import com.homework1.demo2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/7/21.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getUser() {
        return jdbcTemplate.queryForList("SELECT * FROM bqq_user");
    }

    @Override
    public int insertUser(User user) {
        return jdbcTemplate.update("INSERT INTO bqq_user(name,password) VALUES (?,?)",new Object[]{user.getName(),user.getPassword()});
    }

    @Override
    public int updateUser(int id, User user) {
        return jdbcTemplate.update("UPDATE bqq_user SET name =?,password =? WHERE id=?",new Object[]{user.getName(),user.getPassword(),id});
    }

    @Override
    public int deleteUser(int id) {
        return jdbcTemplate.update("DELETE FROM bqq_user WHERE id=?",id);
    }


}
