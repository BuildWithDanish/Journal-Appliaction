package com.danish.journalApp.services;

import com.danish.journalApp.entity.User;
import com.danish.journalApp.repository.JournalEntryRepository;
import com.danish.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    public JournalEntryRepository journalEntryRepository;

    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> updateUser(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByUsername(authentication.getName());
        if (currentUser != null) {
            currentUser.setRoles(Arrays.asList("User"));
            currentUser.setUsername(user.getUsername());
            currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(currentUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public User save(User user) {
        try {
            User savedUser = userRepository.save(user);
            return savedUser;
        } catch (Exception e) {
            log.error("hahahahha");
            log.warn("hahahahha");
            log.trace("hahahahha");
            log.info("hahahahha");
            log.debug("hahahahha");
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> saveAdmin(User user) {
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void deleteByUsername(String username) {
        User user = userRepository.findByUsername(username);
        user.getJournalEntries().forEach(journalEntry -> {
            journalEntryRepository.deleteById(journalEntry.getId());
        });
        userRepository.deleteByUsername(username);
    }
}
