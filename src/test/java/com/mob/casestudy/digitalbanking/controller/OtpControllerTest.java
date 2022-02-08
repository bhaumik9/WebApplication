package com.mob.casestudy.digitalbanking.controller;

import com.mob.casestudy.digitalbanking.dto.CustomerOtpDto;
import com.mob.casestudy.digitalbanking.entity.CustomerOtp;
import com.mob.casestudy.digitalbanking.service.CustomerOtpService;
import com.mob.casestudy.digitalbanking.validation.RequestValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.mob.casestudy.digitalbanking.constants.ErrorCode.LOGIN;

@ExtendWith(MockitoExtension.class)
class OtpControllerTest {

    @InjectMocks
    OtpController otpController;

    @Mock
    CustomerOtpService customerOtpService;

    @Mock
    RequestValidation requestValidation;

    @Test
    void initiateOtp_generateOtp_ReturnsHttpsStatusOk() {

        CustomerOtpDto customerOtpDto=new CustomerOtpDto();
        customerOtpDto.setTemplateId(LOGIN);
        customerOtpDto.setUserName("bhaumik");
        CustomerOtp customerOtpDto1=new CustomerOtp();
//        Mockito.when(requestValidation.validateInitiateOtpRequest(customerOtpDto)).thenReturn(Boolean.TRUE);
        Mockito.when(customerOtpService.getData(customerOtpDto)).thenReturn(customerOtpDto1);
        ResponseEntity responseEntity = otpController.initiateOtp(customerOtpDto);
        Mockito.verify(customerOtpService,Mockito.times(1)).update(customerOtpDto1);
        ResponseEntity responseEntityExpected=new ResponseEntity(HttpStatus.OK);
        Assertions.assertEquals(responseEntityExpected,responseEntity);
    }
}