package com.homework1.demo2.dao;

import com.homework1.demo2.entity.Role;
import com.homework1.demo2.entity.User;


import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/7/21.
 */

public interface RoleDao {
    List<Map<String,Object>> getRole();

    int insertRole(Role role);

    int updateRole(int id,Role role);

    int deleteRole(int id);
}
