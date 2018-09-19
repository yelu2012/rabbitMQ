package com.uws.yl.service.Impl;

import com.uws.yl.mapper.MessageMapper;
import com.uws.yl.bean.MessageInfo;
import com.uws.yl.service.MessageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageInfoServiceImpl implements MessageInfoService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void updateMessageInfo(MessageInfo messageInfo){
        messageMapper.updateMessageInfo(messageInfo);
    }

}
