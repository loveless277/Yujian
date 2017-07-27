package com.homework1.demo2.dao.Impl;

import com.homework1.demo2.dao.ApiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/7/24.
 */
@Repository
public class ApiDaoImpl implements ApiDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getUserMenu(int id) {
        return jdbcTemplate.queryForList("SELECT bqq_user.name,bqq_menu.function FROM bqq_user,bqq_menu,bqq_user_role,bqq_role_menu  " +
                "WHERE bqq_user_role.rid = bqq_role_menu.rid AND bqq_role_menu.mid = bqq_menu.id AND bqq_user_role.uid=bqq_user.id AND bqq_user_role.uid=? ",id);
    }

    @Override
    public List<Map<String, Object>> getUserAll(int id) {
        /*return jdbcTemplate.queryForList("SELECT bqq_user.name,bqq_role.roletype,bqq_menu.function FROM bqq_user,bqq_menu,bqq_role,bqq_user_role,bqq_role_menu "+
                 "WHERE bqq_user_role.rid =bqq_role_menu.rid AND bqq_role_menu.mid = bqq_menu.id AND bqq_user_role.uid=bqq_user.id AND bqq_user_role.rid=bqq_role.id AND bqq_user_role.uid=? ORDER BY bqq_role.roletype",id);*/
        return jdbcTemplate.queryForList("SELECT user.id,user.name,role.roletype,menu.function FROM bqq_user user left join ((bqq_user_role ur left join bqq_role role on ur.rid=role.id)left join(bqq_role_menu rm left join bqq_menu menu on rm.mid=menu.id) on ur.rid=rm.rid)\n" +
                " on user.id=ur.uid WHERE ur.uid=?",id);
    }

    @Override
    public int updateRoleMenu(int rid, int mid) {
        return jdbcTemplate.update("INSERT INTO bqq_role_menu(rid,mid)VALUES (?,?)",rid,mid);
    }

    @Override
    public int updateUserRole(int uid, int rid) {
        return jdbcTemplate.update("INSERT INTO bqq_user_role(uid,rid)VALUES (?,?)",uid,rid);
    }
}
