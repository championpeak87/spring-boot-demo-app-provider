package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.db.TBUser;

public interface IUserRepository extends JpaRepository<TBUser, Integer> {
    
}
