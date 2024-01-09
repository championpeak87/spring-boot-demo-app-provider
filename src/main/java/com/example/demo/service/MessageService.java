package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Message;
import com.example.demo.model.db.TBMessage;
import com.example.demo.repository.IMessageRepository;
import com.example.demo.util.ObjectMapper;

@Service
public class MessageService {

    @Autowired
    private IMessageRepository iMessageRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public Message getMessageById(Integer id) throws Exception {
        TBMessage tbMessage = iMessageRepository.findById(id).orElseThrow(() -> new Exception("Provided id cannot be found!"));

        Message message = objectMapper.convTbToApi(tbMessage);

        return message;
    }

    public void deleteMessageById(Integer id) throws Exception {
        if (!iMessageRepository.existsById(id)) {
            throw new Exception("Provided message with id not found!");
        }

        iMessageRepository.deleteById(id);
    }

    public Message createMessage(Message message) {
        TBMessage tbMessage = objectMapper.convApiToTb(message);

        tbMessage = iMessageRepository.save(tbMessage);

        Message createdMessage = objectMapper.convTbToApi(tbMessage);
        return createdMessage;
    }
}
