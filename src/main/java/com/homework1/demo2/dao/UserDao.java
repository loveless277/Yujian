package com.homework1.demo2.dao;

import com.homework1.demo2.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/7/21.
 */

public interface UserDao {
    List<Map<String,Object>> getUser();

    int insertUser(User user);

    int updateUser(int id,User user);

    int deleteUser(int id);
}
