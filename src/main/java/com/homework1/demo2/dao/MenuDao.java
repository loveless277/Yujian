package com.homework1.demo2.dao;

import com.homework1.demo2.entity.Menu;
import com.homework1.demo2.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/7/21.
 */

public interface MenuDao {
    List<Map<String,Object>> getMenu();

    int insertMenu(Menu menu);

    int updateMenu(int id,Menu menu);

    int deleteMenu(int id);
}
