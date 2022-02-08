package com.mob.casestudy.digitalbanking.controller;

import com.mob.casestudy.digitalbanking.dto.CustomerSecurityQuestionsResponse;
import com.mob.casestudy.digitalbanking.dto.SecurityQuestionsListResponse;
import com.mob.casestudy.digitalbanking.service.SecurityQuestionsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SecurityQuestionsControllerTest {

    @InjectMocks
    SecurityQuestionsController securityQuestionsController;

    @Mock
    SecurityQuestionsService securityQuestionsService;


    @Test
    void getSecurityQuestionsByUsername() {
        CustomerSecurityQuestionsResponse response = new CustomerSecurityQuestionsResponse();
        ResponseEntity<Object> expected=new ResponseEntity<Object>(response, HttpStatus.OK);
        Mockito.when(securityQuestionsController.getSecurityQuestionsByUsername("bhaumik")).thenReturn(expected);
        ResponseEntity<Object> actual = securityQuestionsController.getSecurityQuestionsByUsername("bhaumik");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void getAllSecurityQuestions() {
        SecurityQuestionsListResponse listResponse = new SecurityQuestionsListResponse();

    }
}