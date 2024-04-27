package com.ren.authorityfunction.dao;

import com.Entity.ServiceRobot;

import java.util.List;

public interface AuthorityFunctionDAO_interface {
    // 新增
    public void insert(ServiceRobot.AuthorityFunctionVO authorityFunctionVO);
    // 查詢單項
    public ServiceRobot.AuthorityFunctionVO findByPrimaryKey(Integer authFuncNo);
    // 查詢所有
    public List<ServiceRobot.AuthorityFunctionVO> getAll();
    // 修改
    public void update(ServiceRobot.AuthorityFunctionVO authorityFunctionVO);
    // 刪除
    public void delete(Integer authFuncNo);

}
