package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.db.TBMessage;

public interface IMessageRepository extends JpaRepository<TBMessage, Integer> {
    
}
