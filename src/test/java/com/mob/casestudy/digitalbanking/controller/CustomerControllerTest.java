package com.mob.casestudy.digitalbanking.controller;

import com.mob.casestudy.digitalbanking.dto.CustomerDto;
import com.mob.casestudy.digitalbanking.service.CustomerService;
import com.mob.casestudy.digitalbanking.validation.RequestValidation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @Mock
    private RequestValidation requestValidation;

    @Test
    void addCustomer_Adding_ReturnStatusCreated() {

        CustomerDto customerDto=new CustomerDto();
        customerDto.setFirstName("bhaumik");
        customerDto.setLastName("chhunchha");
        customerDto.setEmail("bhaumik@gmail.com");
        customerDto.setPhoneNumber("9638000000");
        customerDto.setUserName("bhk");
        String id="90ec";
        Mockito.when(customerService.add(customerDto)).thenReturn(id);
        ResponseEntity<Object> responseEntityExpected=new ResponseEntity<>("id "+id, HttpStatus.CREATED);
        ResponseEntity<Object> respnseEntityActual = customerController.addCustomer(customerDto);
        Mockito.verify(requestValidation,Mockito.times(1)).validateCustomer(customerDto);
        assertThat(responseEntityExpected).usingRecursiveComparison().isEqualTo(respnseEntityActual);
    }

    @Test
    void addCustomer_Adding_ReturnStatusInternalError() {
        CustomerDto customerDto=new CustomerDto();
        customerDto.setFirstName("bhaumik");
        customerDto.setLastName("chhunchha");
        customerDto.setEmail("bhaumik@gmail.com");
        customerDto.setPhoneNumber("9638000000");
        customerDto.setUserName("bhk");
        ResponseEntity<Object> expectedResponseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity<Object> actualResponseEntity = customerController.addCustomer(customerDto);
        assertThat(expectedResponseEntity).usingRecursiveComparison().isEqualTo(actualResponseEntity);
    }


    @Test
    void updateCustomer_Updating_ReturnsStatusOk() {
        ResponseEntity responseEntityExpected=new ResponseEntity(HttpStatus.OK);
        String username="bhaumik";
        CustomerDto customerDto=new CustomerDto("bhaumik9","bhaumik","chhunchha","9999998888","bbhaumik@mob.com","EN");
        ResponseEntity<Object> responseEntityActual = customerController.updateCustomer(username, customerDto);
        Mockito.verify(customerService).update(customerDto,username);
        Assertions.assertThat(responseEntityActual).usingRecursiveComparison().isEqualTo(responseEntityExpected);
    }
}