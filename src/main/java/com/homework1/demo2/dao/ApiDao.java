package com.homework1.demo2.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/7/24.
 */
public interface ApiDao {
    List<Map<String,Object>> getUserMenu(int id);
    List<Map<String,Object>> getUserAll(int id);
    int updateRoleMenu(int rid,int mid);
    int updateUserRole(int uid,int rid);
}


