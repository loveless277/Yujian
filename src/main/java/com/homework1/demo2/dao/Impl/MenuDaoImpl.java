package com.homework1.demo2.dao.Impl;

import com.homework1.demo2.dao.MenuDao;
import com.homework1.demo2.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/7/21.
 */
@Repository
public class MenuDaoImpl implements MenuDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getMenu() {
        return jdbcTemplate.queryForList("SELECT * FROM bqq_menu");
    }

    @Override
    public int insertMenu(Menu menu) {
        return jdbcTemplate.update("INSERT INTO bqq_menu(function)VALUES (?)",new Object[]{menu.getFunction()});
    }

    @Override
    public int updateMenu(int id, Menu menu) {
        return jdbcTemplate.update("UPDATE bqq_menu SET function=? WHERE id=?",new Object[]{menu.getFunction(),id});
    }

    @Override
    public int deleteMenu(int id) {
        return jdbcTemplate.update("DELETE FROM bqq_menu WHERE id=?",id);
    }
}
