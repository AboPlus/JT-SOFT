package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.RightsMapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.Rights;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RightsServiceImpl implements RightsService{

    @Autowired
    private RightsMapper rightsMapper;

    @Override
    public List<Rights> list() {
        return rightsMapper.selectList(null);
    }

    /**
     *  1.查询一级列表信息  条件parent_id=0
     *  2.查询以及目录下的二级列表信息    条件parent_id = 1级ID
     * @return
     */
    Integer val = 0;
    @Override
    public List<Rights> getRightsList() {
        QueryWrapper<Rights> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", val);
        List<Rights> list = rightsMapper.selectList(queryWrapper);
        // 遍历list集合数据
        for (Rights rights : list) {
            /*// 根据一级的ID，查询子级信息
            queryWrapper.clear(); //清空第一轮的查询结果  否则会拼接上一轮的条件结果
            queryWrapper.eq("parent_id", rights.getId());
            List<Rights> children = rightsMapper.selectList(queryWrapper);
            //将对象进行封装
            rights.setChildren(children);*/
            val = rights.getId();
            if (rights.getLevel() == 2) break;
            rights.setChildren(getRightsList());
        }
        val = 0;
        return list;
    }
}
