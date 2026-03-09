package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    JournalEntryRepository journalEntryRepository;


    public void post(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void delete(ObjectId id){
        journalEntryRepository.deleteById(id);
    }
    public String update(ObjectId id, JournalEntry newEntry){
        journalEntryRepository.save(newEntry);
        return null;
    }
}
