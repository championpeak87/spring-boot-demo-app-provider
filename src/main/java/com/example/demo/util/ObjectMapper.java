package com.example.demo.util;

import org.springframework.stereotype.Component;

import com.example.demo.model.Message;
import com.example.demo.model.db.TBMessage;

@Component
public class ObjectMapper {
    public Message convTbToApi(TBMessage tbMessage) {
        if (tbMessage == null) {
            return null;
        }

        Message message = new Message();
        message.setId(tbMessage.getId());
        message.setMessage(tbMessage.getMessage());

        return message;
    }

    public TBMessage convApiToTb(Message message) {
        if (message == null) {
            return null;
        }

        TBMessage tbMessage = new TBMessage();
        tbMessage.setId(message.getId());
        tbMessage.setMessage(message.getMessage());

        return tbMessage;
    }
}
