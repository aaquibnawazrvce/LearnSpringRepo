package com.supplierconsumer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.supplierconsumer.mongoobjects.Users;

public interface UserRepository extends MongoRepository<Users, Integer> {
}
