package com.homework1.demo2.service;

import com.homework1.demo2.dao.UserDao;
import com.homework1.demo2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/7/21.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<Map<String,Object>> getUser(){
        return userDao.getUser();
    }

    public int insertUser(User body){
        return userDao.insertUser(body);
    }

    public int updateUser(int id,User body){
        return userDao.updateUser(id,body);
    }

    public int deleteUser(int id){
        return userDao.deleteUser(id);
    }
}
