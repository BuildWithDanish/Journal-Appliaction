package com.danish.journalApp.service;

import com.danish.journalApp.entity.User;
import com.danish.journalApp.repository.UserRepository;
import com.danish.journalApp.services.UserDetailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailServiceImplTest {

    @InjectMocks
    private UserDetailServiceImpl userDetailServiceImpl;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest() {
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("Danish").password("rwsvdsvs").roles(new ArrayList<>()).build());
        userDetailServiceImpl.loadUserByUsername("Bhai");
    }
}
