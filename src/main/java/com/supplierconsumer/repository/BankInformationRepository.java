package com.supplierconsumer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.supplierconsumer.mongoobjects.BankInformation;

@Repository
public interface BankInformationRepository extends MongoRepository<BankInformation, String>  {

}
