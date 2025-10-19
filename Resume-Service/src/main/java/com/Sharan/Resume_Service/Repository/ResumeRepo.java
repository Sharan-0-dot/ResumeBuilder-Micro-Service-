package com.Sharan.Resume_Service.Repository;

import com.Sharan.Resume_Service.Model.Resume;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepo extends MongoRepository<Resume, ObjectId> {
}
