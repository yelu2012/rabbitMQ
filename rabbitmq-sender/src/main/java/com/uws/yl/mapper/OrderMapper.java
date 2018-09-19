package com.uws.yl.mapper;

import com.uws.yl.bean.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

public interface OrderMapper {

    @Insert("INSERT INTO orders(id,name,message_id)values(#{id},#{name},#{messageId})")
    @SelectKey(keyColumn="id",keyProperty = "id",statement = "select REPLACE(UUID(),'-','')  from dual",
            before = true,resultType = String.class)
    void insertOrder(Order order) ;

}
