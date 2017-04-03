package com.rockies.services.impl;

import com.rockies.mapper.MessageHistoryMapper;
import com.rockies.model.MessageHistory;
import com.rockies.services.IMessageHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by MyWay on 2016/9/26.
 */
@Service
public class MessageHistoryService implements IMessageHistoryService {

    @Autowired
    private MessageHistoryMapper messageHistoryMapper;

    @Override
    public void save(MessageHistory messageHistory) {
        if (messageHistory != null) {
            messageHistoryMapper.save(messageHistory);
        }
    }

    @Override
    public List<MessageHistory> get(Map params) {
        return messageHistoryMapper.get(params);
    }
}
