package com.homework1.demo2.service;

import com.homework1.demo2.dao.ApiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by acerpc on 2017/7/24.
 */
@Service
public class ApiService {
    @Autowired
    private ApiDao apiDao;

    public List<Map<String,Object>> getUserMenu(int id){
        /*return apiDao.getUserMenu(id);*/

        List<Map<String, Object>> map = apiDao.getUserMenu(id);
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> resultmap = new LinkedHashMap<>();
        resultmap.put("username",map.get(0).get("name"));
        Set<Object> set = new HashSet<>();
        for (int i = 0; i < map.size(); i++) {
            set.add(map.get(i).get("function"));
        }
        resultmap.put("menus", set);
        result.add(resultmap);
        return result;
    }

    public List<Map<String,Object>> getUserAll(int id){
        /*return apiDao.getUserAll(id);*/
        List<Map<String,Object>> map= apiDao.getUserAll(id);
        if (map.size() == 0) return new ArrayList<>();
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("username",map.get(0).get("name"));
        resultMap.put("role", map.get(0).get("roletype"));
        List<Object> menus = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            if (resultMap.get("role").equals(map.get(i).get("roletype"))) {
                menus.add(map.get(i).get("function"));
            } else {
                resultMap.put("menus", menus);
                result.add(resultMap);//第一行
                //初始化第2，3，。。。行
                menus = new ArrayList<>();
                menus.add(map.get(i).get("function"));
                resultMap = new LinkedHashMap<>();
                resultMap.put("role", map.get(i).get("roletype"));
            }
        }
        resultMap.put("menus", menus);
        result.add(resultMap);//第一行
        return result;
    }

    public int updateRoleMenu(int rid,int mid){
        return apiDao.updateRoleMenu(rid,mid);
    }

    public int updateUserRole(int uid,int rid){
        return apiDao.updateUserRole(uid,rid);
    }
}
