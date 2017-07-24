package com.homework1.demo2.service;

import com.homework1.demo2.dao.RoleDao;
import com.homework1.demo2.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/7/21.
 */
@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public List<Map<String,Object>> getRole(){
        return roleDao.getRole();
    }

    public int insertRole(Role body){
        return roleDao.insertRole(body);
    }

    public int updateRole(int id,Role body){
        return roleDao.updateRole(id,body);
    }

    public int deleteRole(int id){
        return roleDao.deleteRole(id);
    }
}
