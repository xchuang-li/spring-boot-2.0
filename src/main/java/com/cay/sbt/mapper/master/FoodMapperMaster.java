package com.cay.sbt.mapper.master;

import com.cay.sbt.entity.Food;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface FoodMapperMaster {
    @Select("select * from food")
    List<Food> selectAllFood();

    @Select("select * from food where id = #{id}")
    Food selectOneFood(Integer id);

    @Select("select * from food where pro_time = #{proTime} and exp_date = #{expDate}")
    Food selectOneFoodByProTimeAndExpDate(@Param("proTime") String proTime,@Param("expDate") String expDate);

    List<Food> selectFoodByDynamic(Map<String,Object> map);

    int updateFoodByDynamic(Map<String,Object> map);

    List<Food> selectFoodByContain(List<Integer> list);

    @Insert("insert into food values(null,#{name},#{proTime},#{expDate})")
    int insertFoodByMap(Map<String,Object> map);
}
