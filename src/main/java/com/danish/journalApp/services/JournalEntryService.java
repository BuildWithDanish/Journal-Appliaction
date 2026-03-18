package com.danish.journalApp.services;

import com.danish.journalApp.entity.JournalEntry;
import com.danish.journalApp.entity.User;
import com.danish.journalApp.repository.JournalEntryRepository;
import com.danish.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        try {
            User user = userRepository.findByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(journalEntry);
            userRepository.save(user);
            journalEntryRepository.save(journalEntry);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean delete(ObjectId id, String username) {
        boolean removed = false;
        try {
            User user = userRepository.findByUsername(username);
            removed = user.getJournalEntries().removeIf(journalEntry -> journalEntry.getId().equals(id));
            if (removed) {
                userService.save(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return removed;
    }

    public String update(ObjectId id, JournalEntry newEntry) {
        newEntry.setDate(LocalDateTime.now());
        journalEntryRepository.save(newEntry);
        return null;
    }
}
