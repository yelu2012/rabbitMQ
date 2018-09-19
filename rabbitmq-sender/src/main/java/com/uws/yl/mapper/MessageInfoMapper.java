package com.uws.yl.mapper;

import com.uws.yl.bean.MessageInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MessageInfoMapper {

    @Insert("insert into message_info(id,message,count,next_time,status ,message_id,create_time,update_time)" +
            "values(#{id},#{message},#{count},#{nextTime},#{status},#{messageId},#{createTime},#{updateTime})")
    @SelectKey(statement = "select REPLACE(UUID(),'-','')  from dual ",keyProperty = "id",keyColumn = "id",before = true,resultType = String.class)
    void insertMessageInfo(MessageInfo messageInfo);

    @Update("update message_info set status = #{status} where message_id=#{messageId}")
    void updateMessageInfo(MessageInfo messageInfo);

    @Update("update message_info set count = #{count} where message_id=#{messageId}")
    void updataMessageSendConut(MessageInfo messageInfo);

    @Select("select * from message_info where status = 0 and next_time <= SYSDATE() ")
    List<MessageInfo> getListMessageInfo();
}
