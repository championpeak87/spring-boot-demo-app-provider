package com.example.demo.dataapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Message;

import reactor.core.publisher.Mono;

public interface IMessageApi {
    @GetMapping(value = "/message/{id}", produces = "application/json")
    ResponseEntity<Mono<Message>> getMessageById(@PathVariable(name = "id") Integer id) throws Exception;

    @PostMapping(value = "/message", consumes = "application/json")
    ResponseEntity<Void> createMessage(@RequestBody Message message) throws Exception;

    @DeleteMapping("/message/{id}")
    ResponseEntity<Void> deleteMessageById(@PathVariable(name = "id") Integer id) throws Exception;
}
