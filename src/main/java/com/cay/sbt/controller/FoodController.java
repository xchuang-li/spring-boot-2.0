package com.cay.sbt.controller;

import com.cay.sbt.Service.FoodService;
import com.cay.sbt.entity.Food;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
public class FoodController {
    @Autowired
    FoodService foodService;

    @Autowired
    //当存在多个实现类时，标示哪个实现类是被需要的，此处使用自定义Bean
    @Qualifier("customRedisTemplate")
    private RedisTemplate<String,Object> rst;

    @RequestMapping("/selectAllFood")
    public List<Food> selectAllFood(){
        Page<Food> page = PageHelper.startPage(2,3);
        return foodService.selectAllFood();
    }

    @RequestMapping("/selectOneFood")
    public Food selectOneFood(@RequestParam(value = "id") Integer id){
//        if(rst.hasKey("food["+id+"]")){
//            log.info("缓存中存在 id:="+id+"的Food! 从缓存中获取Food,key:="+"food["+id+"]");
//            return (Food) rst.opsForValue().get("food["+id+"]");
//        }else {
//            log.info("缓存中不存在id:="+id+"的Food,从数据库获取，并存入缓存，key:="+"food["+id+"]");
//            Food food = foodService.selectOneFood(id);
//            rst.opsForValue().set("food["+id+"]",food);
//            return food;
//        }
        log.info("我还是个宝宝啊");
        return foodService.selectOneFood(id);
    }

    @RequestMapping("/selectOneFoodByProTimeAndExpDate")
    public Food selectOneFoodByProTimeAndExpDate(@RequestParam("proTime") String proTime,@RequestParam("expDate") String expDate){
        return foodService.selectOneFoodByProTimeAndExpDate(proTime,expDate);
    }
    @RequestMapping("/selectFoodByDynamic")
    public List<Food> selectFoodByDynamic(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","orange");
        map.put("proTime","20190409");
        if(!rst.hasKey("food["+map.get("name")+"]")){
            log.info("redis add key:="+"food["+map.get("name")+"]");
            rst.opsForValue().set("food["+map.get("name")+"]",map.toString());
        }else{
            log.info("key:="+"food["+map.get("name")+"]" + " value:="+rst.opsForValue().get("food["+map.get("name")+"]"));
        }
        return foodService.selectFoodByDynamic(map);
    }
    @Transactional
    @RequestMapping("/updateFoodByDynamic")
    public int updateFoodByDynamic(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",2);
        map.put("name","banana");
        map.put("proTime","20190409");

        Food food = new Food();

        log.info("更新Food,并存入缓存,key:=food["+(Integer) map.get("id")+"]");
        if(rst.hasKey("food["+map.get("id")+"]")){
            food = (Food) rst.opsForValue().get("food["+(Integer) map.get("id")+"]");
        }else{
            food = foodService.selectOneFood((Integer) map.get("id"));
        }

        if (map.get("id")!=null){
            food.setId((Integer) map.get("id"));
        }
        if (map.get("name")!=null){
            food.setName((String) map.get("name"));
        }
        if (map.get("proTime")!=null){
            food.setProTime((String) map.get("proTime"));
        }
        if (map.get("expDate")!=null){
            food.setExpDate((String) map.get("expDate"));
        }
        log.info("更新或者插入缓存food,key:="+"food["+map.get("id")+"]");
        rst.opsForValue().set("food["+map.get("id")+"]",food);
        return foodService.updateFoodByDynamic(map);
    }
    @RequestMapping("/selectFoodByContain")
    public List<Food> selectFoodByContain(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        return foodService.selectFoodByContain(list);
    }

    @RequestMapping("/insertFoodByMap")
    @Transactional(transactionManager = "masterDataSourceTransactionManager")
    public Integer insertFoodByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","banana");
        map.put("proTime","20190409");
        map.put("expDate","0.5");
        int i =foodService.insertFoodByMap(map);

        int k =1/0;
        return i;
    }
}
