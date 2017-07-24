package com.homework1.demo2.dao.Impl;

import com.homework1.demo2.dao.RoleDao;
import com.homework1.demo2.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/7/21.
 */
@Repository
public class RoleDaoImpl implements RoleDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getRole() {
        return jdbcTemplate.queryForList("SELECT * FROM bqq_role");
    }

    @Override
    public int insertRole(Role role) {
        return jdbcTemplate.update("INSERT INTO bqq_role(roletype)VALUES (?)",new Object[]{role.getRoletype()});
    }

    @Override
    public int updateRole(int id, Role role) {
        return jdbcTemplate.update("UPDATE bqq_role SET roletype=? WHERE id=?",new Object[]{role.getRoletype(),id});
    }

    @Override
    public int deleteRole(int id) {
        return jdbcTemplate.update("DELETE FROM bqq_role WHERE id=?",id);
    }
}
