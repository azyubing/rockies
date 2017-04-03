package com.rockies.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.model.MessageHistory;

/**
 * Created by MyWay on 2016/9/26.
 */
public interface MessageHistoryMapper {

    void save(MessageHistory messageHistory);

    List<MessageHistory> get(Map params);

}
