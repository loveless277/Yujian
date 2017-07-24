package com.homework1.demo2.service;

import com.homework1.demo2.dao.ApiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/7/24.
 */
@Service
public class ApiService {
    @Autowired
    private ApiDao apiDao;

    public List<Map<String,Object>> getUserMenu(int id){
        return apiDao.getUserMenu(id);
    }

    public List<Map<String,Object>> getUserAll(int id){
        return apiDao.getUserAll(id);
    }

    public int updateRoleMenu(int rid,int mid){
        return apiDao.updateRoleMenu(rid,mid);
    }

    public int updateUserRole(int uid,int rid){
        return apiDao.updateUserRole(uid,rid);
    }
}
