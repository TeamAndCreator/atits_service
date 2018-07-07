package com.atits.service;


import com.atits.dao.RoleDao;
import com.atits.entity.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class RoleService {

    @Resource
    private RoleDao roleDao;

    /**
     * 查询所有角色
     * @return List
     */
    public List findAll(){
        return roleDao.findAll();
    }

    public Role findById(Integer roleId){return roleDao.findById(roleId);}
}
