package com.uws.yl.mapper;

import com.uws.yl.bean.MessageInfo;
import org.apache.ibatis.annotations.Update;

public interface MessageMapper {
    @Update("update message_info set status = #{status} where message_id=#{messageId}")
    void updateMessageInfo(MessageInfo messageInfo);
}
