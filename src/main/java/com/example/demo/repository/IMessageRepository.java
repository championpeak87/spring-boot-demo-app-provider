package com.example.demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.demo.model.db.TBMessage;

public interface IMessageRepository extends ReactiveCrudRepository<TBMessage, Integer> {
    
}
