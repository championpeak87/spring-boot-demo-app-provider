package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Message;
import com.example.demo.model.db.TBMessage;
import com.example.demo.repository.IMessageRepository;
import com.example.demo.util.ObjectMapper;

import reactor.core.publisher.Mono;

@Service
public class MessageService {

    @Autowired
    private IMessageRepository iMessageRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public Mono<Message> getMessageById(Integer id) throws Exception {
        Mono<TBMessage> tbMessage = iMessageRepository.findById(id);

        return tbMessage.map(objectMapper::convTbToApi);
    }

    public void deleteMessageById(Integer id) throws Exception {
        if (!iMessageRepository.existsById(id).block()) {
            throw new Exception("Provided message with id not found!");
        }

        iMessageRepository.deleteById(id);
    }

    public Mono<Message> createMessage(Message message) {
        TBMessage tbMessage = objectMapper.convApiToTb(message);

        Mono<TBMessage> savedMessage = iMessageRepository.save(tbMessage);

        return savedMessage.map(objectMapper::convTbToApi);
    }
}
