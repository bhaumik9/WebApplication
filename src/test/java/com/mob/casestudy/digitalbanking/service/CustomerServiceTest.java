package com.mob.casestudy.digitalbanking.service;

import com.mob.casestudy.digitalbanking.dto.CustomerDto;
import com.mob.casestudy.digitalbanking.entity.Customer;
import com.mob.casestudy.digitalbanking.enums.CustomerStatus;
import com.mob.casestudy.digitalbanking.exception.CustomerNotFound;
import com.mob.casestudy.digitalbanking.exception.InvalidFieldException;
import com.mob.casestudy.digitalbanking.exception.MandatoryFieldMissingException;
import com.mob.casestudy.digitalbanking.repository.CustomerRepo;
import com.mob.casestudy.digitalbanking.util.Util;
import com.mob.casestudy.digitalbanking.validation.RequestValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;
    @Mock
    CustomerRepo customerRepo;
    @Mock
    RequestValidation requestValidation;
    @Mock
    Util util;

    @Test
    void generateOtp_GeneratingRandomNumber_ReturnsString() {
        String expected="999999";
        Mockito.when(util.getSecureRandom()).thenReturn(999999);
        String actual = customerService.generateOtp();
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void add() {
        CustomerDto customerDto=new CustomerDto("bhaumik9","bhaumik","chhunchha","9999999999","bhaumik@mob.com","EN");
        Customer customer=customerDto.toEntity();
        customer.setStatus(CustomerStatus.ACTIVE.toString());
        customer.setExternalId(customer.getUserName() + "_ext");
        customer.setCreatedBy(System.getProperty("user.name"));
        customer.setUpdatedBy(System.getProperty("user.name"));
        customer.setCreatedOn(LocalDateTime.now());
        customer.setUpdatedOn(LocalDateTime.now());
        Customer savedCustomer=new Customer();
        savedCustomer.setId("123");
        Mockito.when(customerRepo.save(Mockito.any(Customer.class))).thenReturn(savedCustomer);
        String actualID = customerService.add(customerDto);
        String expectedID="123";
        Assertions.assertEquals(expectedID,actualID);
    }
    @Test
    void update_UpdatingCustomer_ReturnsVoid() {
        CustomerDto customerDto=new CustomerDto("bhaumik9","bhaumik","chhunchha","9999999999","bhaumik@mob.com","EN");
        String username="bhaumik9";
        Customer customer=new Customer();
        Mockito.when(requestValidation.validateUserNameInDatabase(username,"update")).thenReturn(customer);
        Mockito.when(requestValidation.validatePhoneNumber(customerDto.getPhoneNumber())).thenReturn(Boolean.TRUE);
        Mockito.when(requestValidation.validateEmail(customerDto.getEmail())).thenReturn(Boolean.TRUE);
        Mockito.when(requestValidation.validatePreferredLanguage(customerDto.getPreferredLanguage())).thenReturn(Boolean.TRUE);
//        Mockito.when(requestValidation.validateStatus(customerDto.getStatus())).thenReturn(Boolean.TRUE);
        customerService.update(customerDto,username);
        Mockito.verify(customerRepo).save(customer);

    }
    @Test
    void update_UpdatingCustomer_ReturnsMandatoryFieldMissingException() {
        CustomerDto customerDto=new CustomerDto("bhaumik9","bhaumik","chhunchha","9999999999","bhaumik@mob.com","EN");
        String username= null;
        Mockito.when(requestValidation.validateUserNameInDatabase(username,"update")).thenThrow(MandatoryFieldMissingException.class);
        Assertions.assertThrows(MandatoryFieldMissingException.class,()->customerService.update(customerDto,username));
    }
    @Test
    void update_UpdatingCustomer_ReturnsCustomerNotFoundException() {
        CustomerDto customerDto=new CustomerDto("bhaumik9","bhaumik","chhunchha","9999999999","bhaumik@mob.com","EN");
        String username= "demo";
        Mockito.when(requestValidation.validateUserNameInDatabase(username,"update")).thenThrow(CustomerNotFound.class);
        Assertions.assertThrows(CustomerNotFound.class,()->customerService.update(customerDto,username));
    }
    @Test
    void update_UpdatingCustomerPhoneNumber_ReturnsInvalidFieldException() {
        CustomerDto customerDto=new CustomerDto("bhaumik9","bhaumik","chhunchha","9999","bhaumik@mob.com","EN");
        String username= "demo";
        Customer customer=new Customer();
        Mockito.when(requestValidation.validateUserNameInDatabase(username,"update")).thenReturn(customer);
        Mockito.when(requestValidation.validatePhoneNumber(customerDto.getPhoneNumber())).thenThrow(InvalidFieldException.class);
        Assertions.assertThrows(InvalidFieldException.class,()->customerService.update(customerDto,username));
    }
    @Test
    void update_UpdatingCustomerEmail_ReturnsInvalidFieldException() {
        CustomerDto customerDto=new CustomerDto("bhaumik9","bhaumik","chhunchha","9999","bhaumik@mob.com","EN");
        String username= "demo";
        Customer customer=new Customer();
        Mockito.when(requestValidation.validateUserNameInDatabase(username,"update")).thenReturn(customer);
        Mockito.when(requestValidation.validateEmail(customerDto.getEmail())).thenThrow(InvalidFieldException.class);
        Assertions.assertThrows(InvalidFieldException.class,()->customerService.update(customerDto,username));
    }
    @Test
    void update_UpdatingCustomerPreferredLanguage_ReturnsInvalidFieldException() {
        CustomerDto customerDto=new CustomerDto("bhaumik9","bhaumik","chhunchha","9999","bhaumik@mob.com","EN");
        String username= "demo";
        Customer customer=new Customer();
        Mockito.when(requestValidation.validateUserNameInDatabase(username,"update")).thenReturn(customer);
        Mockito.when(requestValidation.validatePreferredLanguage(customerDto.getPreferredLanguage())).thenThrow(InvalidFieldException.class);
        Assertions.assertThrows(InvalidFieldException.class,()->customerService.update(customerDto,username));
    }
    @Test
    void update_UpdatingCustomerStatusTrue_ReturnsVoid() {
        CustomerDto customerDto=new CustomerDto("bhaumik9","bhaumik","chhunchha","9999","bhaumik@mob.com","EN");
        customerDto.setStatus("ACTIVE");
        String username= "demo";
        Customer customer=new Customer();
        Mockito.when(requestValidation.validateUserNameInDatabase(username,"update")).thenReturn(customer);
        Mockito.when(requestValidation.validateStatus(customerDto.getStatus())).thenReturn(Boolean.TRUE);
        customerService.update(customerDto,username);
        Mockito.verify(customerRepo).save(customer);
    }
    @Test
    void update_UpdatingCustomerStatusFalse_ReturnsVoid() {
        CustomerDto customerDto=new CustomerDto("bhaumik9","bhaumik","chhunchha","9999","bhaumik@mob.com","EN");
        customerDto.setStatus("ACTIVE");
        String username= "demo";
        Customer customer=new Customer();
        Mockito.when(requestValidation.validateUserNameInDatabase(username,"update")).thenReturn(customer);
        Mockito.when(requestValidation.validateStatus(customerDto.getStatus())).thenReturn(Boolean.TRUE);
        customerService.update(customerDto,username);
        Mockito.verify(customerRepo).save(customer);
    }
}