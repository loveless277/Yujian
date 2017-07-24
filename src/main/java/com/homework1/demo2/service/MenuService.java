package com.homework1.demo2.service;

import com.homework1.demo2.dao.MenuDao;
import com.homework1.demo2.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by acerpc on 2017/7/21.
 */
@Service
public class MenuService {
    @Autowired
    private MenuDao menuDao;

    public List<Map<String,Object>> getMenu(){
        return menuDao.getMenu();
    }

    public int insertMenu(Menu body){
        return menuDao.insertMenu(body);
    }

    public int updateMenu(int id,Menu body){
        return menuDao.updateMenu(id,body);
    }

    public int deleteMenu(int id){
        return menuDao.deleteMenu(id);
    }
}
