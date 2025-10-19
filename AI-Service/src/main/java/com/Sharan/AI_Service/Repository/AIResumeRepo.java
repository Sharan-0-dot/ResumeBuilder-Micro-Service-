package com.Sharan.AI_Service.Repository;

import com.Sharan.AI_Service.Model.AiResponse;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AIResumeRepo extends MongoRepository<AiResponse, ObjectId> {
}
