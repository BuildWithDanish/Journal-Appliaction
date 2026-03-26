package com.danish.journalApp.repository;

import com.danish.journalApp.entity.ConfigJournalAppEntity;
import com.danish.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppConfigRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}
