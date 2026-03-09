package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.JournalEntry;
import net.engineeringdigest.journalApp.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getJournalEntries() {
        return journalEntryService.getAll();
    }

    @GetMapping("/id/{id}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId id) {
        return journalEntryService.getJournalEntryById(id);
    }
    @PostMapping
    public boolean createJournalEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.post(myEntry);
        return true;
    }
    @PutMapping("/id/{id}")
    public String updateJournalEntry(@RequestBody JournalEntry journalEntry, ObjectId id) {
        return journalEntryService.update(id, journalEntry);
    }
    @DeleteMapping("/delete/{id}")
    public boolean deleteJournalEntry(@PathVariable ObjectId id) {
        journalEntryService.delete(id);
        return true;
    }
}
