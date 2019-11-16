package com.supplierconsumer.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.supplierconsumer.mongoobjects.Login;

@Repository
public interface LoginRepository extends MongoRepository<Login, Integer>  {

	Login findByUserName(String userName);

}
