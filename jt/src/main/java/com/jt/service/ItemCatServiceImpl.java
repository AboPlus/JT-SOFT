package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemCatServiceImpl implements ItemCatService{
    @Autowired
    private ItemCatMapper itemCatMapper;


    /**
     * 问题分析：
     *      1.提高程序的效率  减少数据库交互的次数
     *      2.查询的方法最好单独的抽取
     * 问题：如何有效的存储父子关系
     * 数据结构：Map<key,value>  ———— 设计成：Map<parentId,子级>
     * 说明：设计成 Map<parentId,子级> 之后，map.get(parentId)拿到的就是子级
     *      例如：
     *          Map<0,List<ItemCat>>        这里List<ItemCat>是一级目录    通过map.get(0)拿到一级目录
     *          Map<一级id,List<ItemCat>>   这里List<ItemCat>是二级目录    通过map.get(0)拿到二级目录
     *          Map<二级id,List<ItemCat>>   这里List<ItemCat>是三级目录    通过map.get(0)拿到三级目录
     */
    // 一、封装一个Map集合
    public Map<Integer,List<ItemCat>> getmap () {
        Map<Integer,List<ItemCat>> map = new HashMap<>();
        // 1.查询所有的数据信息
        List<ItemCat> list = itemCatMapper.selectList(null);
        // 2.将List集合封装到Map集合中
        for (ItemCat itemCat : list) {
            // 规则：判断map中是否有key
            // 没有key：该子级是第一个父级元素的孩子，应该声明父级并且将子级作为第一个子级
            // 有key：我找到父级的自己序列 将子级追加到序列中即可
            if (map.containsKey(itemCat.getParentId())){
                // 有父级
                // 获取父级的所有已知子级 . 追加一个itemCat
                 map.get(itemCat.getParentId()).add(itemCat);
            }else {
                // 没有父级
                List<ItemCat> initList = new ArrayList<>();
                initList.add(itemCat);
                map.put(itemCat.getParentId(),initList);
            }
        }
        return map;
    }
    // 商品分类查询 方式二：HashMap方法实现  只查询一次数据库   步骤：一、二、三
    @Override
    public List<ItemCat> findItemCatList(Integer type) {
        // 获取数据封装后的结果
        Map<Integer,List<ItemCat>> map = getmap();
        List<ItemCat> catList = new ArrayList<>();
        if (type == 1) {    //获取一级商品分类信息
            return map.get(0);
        }
        if (type == 2) {    //获取二级商品分类信息
            return getLevel2(map);
        }
            return getLevel3(map);  // type不为1、2直接返回三级分类方法
    }

    // 二、获取二级商品分类的方法
    private List<ItemCat> getLevel2(Map<Integer, List<ItemCat>> map) {
        // 1.获取1级商品分类信息
        List<ItemCat> list = map.get(0);
        // 2.封装2级菜单信息
        for (ItemCat itemCat : list) {  // 遍历的是一级分类
            // 如何获取该一级菜单的子级？ 二级菜单的parentId是一级菜单的ID
            List<ItemCat> twoList = map.get(itemCat.getId());
            itemCat.setChildren(twoList);
        }
        return list;
    }

    // 三、获取三级商品分类的方法
    private List<ItemCat> getLevel3(Map<Integer, List<ItemCat>> map) {
        // 1.获取二级商品分类信息  一级里面套二级
        List<ItemCat> list = getLevel2(map);
        for (ItemCat itemCat1 : list) {
            if (itemCat1.getChildren() != null){
                List<ItemCat> list2 = itemCat1.getChildren();
                // 根据2级查询3级信息
                for (ItemCat itemCat2 : list2) {
                    List<ItemCat> list3 = map.get(itemCat2.getId());
                    itemCat2.setChildren(list3);
                }
                // 已经将3级封装完成，将二级封装到一级中
                itemCat1.setChildren(list2);
            }
        }
        return list;
    }

    // 商品分类查询 方式一：递归实现
    /*Integer parentId = 0;
    @Override
    public List<ItemCat> findItemCatList(Integer type) {
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        List<ItemCat> itemCats = itemCatMapper.selectList(queryWrapper);
        for (ItemCat itemCat : itemCats){
            if (itemCat.getLevel() == type) {
                break;
            }
            parentId = itemCat.getId();
            itemCat.setChildren(findItemCatList(type));
        }
        parentId = 0;
        return itemCats;
    }*/

    @Override
    @Transactional
    public Boolean updateStatus(ItemCat itemCat) {
        int rows = itemCatMapper.updateById(itemCat);
        if (rows == 0) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean saveItemCat(ItemCat itemCat) {
        itemCat.setStatus(true);
        int rows = itemCatMapper.insert(itemCat);
        if (rows == 0){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean updateItemCat(ItemCat itemCat) {
        int rows = itemCatMapper.updateById(itemCat);
        if (rows == 0) {
            return false;
        }
        return true;
    }

    @Override
    public void deleteItemCat(Integer id, Integer level) {
        if(level == 3){
            //如果是三级商品分类菜单则直接删除
            itemCatMapper.deleteById(id);
        }

        if(level == 2){
            //先删除3级菜单
            QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id",id);
            itemCatMapper.delete(queryWrapper);
            //先删除2级菜单
            itemCatMapper.deleteById(id);
        }

        if(level == 1) {
            //1.查询二级分类信息 parent_id=一级ID
            QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id", id);
            //获取主键信息(第一列信息)
            List<Object> twoIdList = itemCatMapper.selectObjs(queryWrapper);
            //2.先删除3级
            for (Object twoId : twoIdList) {
                QueryWrapper<ItemCat> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("parent_id", twoId);
                itemCatMapper.delete(queryWrapper2);
                //将2级删除
                Integer intTwoId = (Integer) twoId;
                itemCatMapper.deleteById(intTwoId);
            }
            //3.删除一级商品分类信息
            itemCatMapper.deleteById(id);
        }
    }

}
