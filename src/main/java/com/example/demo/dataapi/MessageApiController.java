package com.example.demo.dataapi;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Message;
import com.example.demo.service.MessageService;

@RestController
public class MessageApiController implements IMessageApi {

    @Autowired
    private MessageService messageService;

    @Override
    public ResponseEntity<Void> createMessage(Message message) throws Exception {
        Message createdMessage = messageService.createMessage(message);

        return ResponseEntity.created(URI.create(String.format("localhost:8000/message/%d", createdMessage.getId()))).build();
    }

    @Override
    public ResponseEntity<Void> deleteMessageById(Integer id) throws Exception {
        messageService.deleteMessageById(id);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Message> getMessageById(Integer id) throws Exception {
        Message message = messageService.getMessageById(id);

        return ResponseEntity.ok(message);
    }
}
