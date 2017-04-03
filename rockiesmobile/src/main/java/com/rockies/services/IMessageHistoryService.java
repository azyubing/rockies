package com.rockies.services;

import com.rockies.model.MessageHistory;

import java.util.List;
import java.util.Map;

/**
 * Created by MyWay on 2016/9/26.
 */
public interface IMessageHistoryService {

    String TYPE_CUSTOM = "CUSTOM";

    void save(MessageHistory messageHistory);

    List<MessageHistory> get(Map params);
}
