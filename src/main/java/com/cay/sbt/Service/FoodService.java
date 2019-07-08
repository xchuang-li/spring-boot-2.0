package com.cay.sbt.Service;

import com.cay.sbt.entity.Food;
import com.cay.sbt.mapper.master.FoodMapperMaster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class FoodService {
    @Autowired(required = false)
    FoodMapperMaster foodMapper;

    public List<Food> selectAllFood(){
        return foodMapper.selectAllFood();
    }

    @Cacheable(value = "Food")
    public Food selectOneFood(Integer id){
        log.info("查询数据库！");
        return foodMapper.selectOneFood(id);
    }

    public Food selectOneFoodByProTimeAndExpDate(String proTime,String expDate){
        return foodMapper.selectOneFoodByProTimeAndExpDate(proTime,expDate);
    }

    public List<Food> selectFoodByDynamic(Map<String,Object> map){
        return foodMapper.selectFoodByDynamic(map);
    }

    public int updateFoodByDynamic(Map<String,Object> map){
        return foodMapper.updateFoodByDynamic(map);
    }

    public List<Food> selectFoodByContain(List<Integer> list){
        return foodMapper.selectFoodByContain(list);
    }
    public Integer insertFoodByMap(Map<String,Object> map){
        return foodMapper.insertFoodByMap(map);
    }
}
