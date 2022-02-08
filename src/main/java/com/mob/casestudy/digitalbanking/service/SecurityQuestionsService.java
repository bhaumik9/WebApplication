package com.mob.casestudy.digitalbanking.service;

import com.mob.casestudy.digitalbanking.dto.CustomerSecurityQuestionsResponse;
import com.mob.casestudy.digitalbanking.dto.SecurityQuestionsDto;
import com.mob.casestudy.digitalbanking.dto.SecurityQuestionsListResponse;
import com.mob.casestudy.digitalbanking.entity.Customer;
import com.mob.casestudy.digitalbanking.entity.CustomerSecurityQuestions;
import com.mob.casestudy.digitalbanking.entity.SecurityQuestion;
import com.mob.casestudy.digitalbanking.exception.SecurityQuestionsNotFound;
import com.mob.casestudy.digitalbanking.repository.CustomerSecurityQuestionsRepo;
import com.mob.casestudy.digitalbanking.repository.SecurityQuestionsRepo;
import com.mob.casestudy.digitalbanking.validation.RequestValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SecurityQuestionsService {

    private SecurityQuestionsRepo securityQuestionsRepo;
    private RequestValidation requestValidation;
    private CustomerSecurityQuestionsRepo customerSecurityQuestionsRepo;

    public SecurityQuestionsService(SecurityQuestionsRepo securityQuestionsRepo, RequestValidation requestValidation, CustomerSecurityQuestionsRepo customerSecurityQuestionsRepo) {
        this.securityQuestionsRepo = securityQuestionsRepo;
        this.requestValidation = requestValidation;
        this.customerSecurityQuestionsRepo = customerSecurityQuestionsRepo;
    }

    public ResponseEntity<Object> getAllSecurityQuestions() {
        List<SecurityQuestion> list = securityQuestionsRepo.findAll();
        if (list.isEmpty()) {
            throw new SecurityQuestionsNotFound();
        }
        SecurityQuestionsListResponse listResponse = new SecurityQuestionsListResponse();
        list.forEach(l -> {
            SecurityQuestionsDto securityQuestionsDto1 = l.toDto();
            listResponse.addQuestion(securityQuestionsDto1);
        });
        return ResponseEntity.ok().body(listResponse);
    }

    public ResponseEntity<Object> addQuestion(String question) {
        SecurityQuestion securityQuestion = new SecurityQuestion();
        securityQuestion.setSecurityQuestionText(question);
        securityQuestionsRepo.save(securityQuestion);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> getSecurityQuestionsByUsername(String username) {
        Customer customer = requestValidation.validateUserNameInDatabase(username, "securityQuestion");
        List<CustomerSecurityQuestions> customerSecurityQuestionsList = customer.getCustomerSecurityQuestionsList();
        if (customerSecurityQuestionsList.isEmpty()) {
            throw new SecurityQuestionsNotFound();
        }
        CustomerSecurityQuestionsResponse response = new CustomerSecurityQuestionsResponse();
        customerSecurityQuestionsList.forEach(l -> response.addData(l.toDto()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Object> addSecurityQuestionsByUsername(String username) {
        Customer customer = requestValidation.validateUserNameInDatabase(username, "securityQuestion");
        List<CustomerSecurityQuestions> customerSecurityQuestions = setSecurityQuestions(customer);
        customer.setCustomerSecurityQuestionsList(customerSecurityQuestions);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private List<CustomerSecurityQuestions> setSecurityQuestions(Customer customer) {
        List<CustomerSecurityQuestions> list = new ArrayList<>();
        CustomerSecurityQuestions customerSecurityQuestions = new CustomerSecurityQuestions();
        Optional<SecurityQuestion> securityQuestion = securityQuestionsRepo.findById("1bf0e080-fb82-49c4-8039-d6e6977d093e");
        if (securityQuestion.isPresent()) {
            customerSecurityQuestions.setCustomer(customer);
            customerSecurityQuestions.setSecurityQuestion(securityQuestion.get());
            customerSecurityQuestions.setSecurityQuestionAnswer("Mustang GT");
            customerSecurityQuestionsRepo.save(customerSecurityQuestions);
        }
        list.add(customerSecurityQuestions);
        return list;
    }
}
