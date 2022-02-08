package com.mob.casestudy.digitalbanking.service;

import com.mob.casestudy.digitalbanking.dto.CustomerOtpDto;
import com.mob.casestudy.digitalbanking.entity.Customer;
import com.mob.casestudy.digitalbanking.entity.CustomerOtp;
import com.mob.casestudy.digitalbanking.repository.CustomerOtpRepo;
import com.mob.casestudy.digitalbanking.repository.CustomerRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.mob.casestudy.digitalbanking.constants.ErrorCode.LOGIN;
import static com.mob.casestudy.digitalbanking.constants.ErrorCode.REGISTRATION;

@Service
@NoArgsConstructor
@PropertySource("classpath:application.yml")
public class CustomerOtpService {

    @Value("${otp.default}")
    private String messageDefault;
    @Value("${otp.registration}")
    private String messageRegistration;
    @Value("${otp.login}")
    private String messageLogin;
    private CustomerService customerService;
    private CustomerRepo customerRepo;
    private CustomerOtpRepo customerOtpRepo;

    @Autowired
    public CustomerOtpService(CustomerService customerService, CustomerRepo customerRepo, CustomerOtpRepo customerOtpRepo) {
        this.customerService = customerService;
        this.customerRepo = customerRepo;
        this.customerOtpRepo = customerOtpRepo;
    }

    public CustomerOtp getData(CustomerOtpDto customerOtpDto) {
        String otp = customerService.generateOtp();
        String templateId = customerOtpDto.getTemplateId();
        //TODO: USer concat operation
        String message;
        if (templateId.equals(REGISTRATION)) {
            message = messageRegistration.concat(" ").concat(otp);
        } else if (templateId.equals(LOGIN)) {
            message = messageLogin.concat(" ").concat(otp);
        } else {
            message = messageDefault.concat(" ").concat(otp);
        }
        LocalDateTime otpTimeCreated = LocalDateTime.now();
        LocalDateTime otpTimeExpired = otpTimeCreated.plusMinutes(5);
        return new CustomerOtp(message, otp, 0, otpTimeExpired, otpTimeCreated, customerOtpDto.getUserName());
    }

    public void update(CustomerOtp customerOtp) {
        Customer customer = customerRepo.findByUserName(customerOtp.getUserName());
        CustomerOtp customerOtpDb = customer.getCustomerOtp();
        if (customerOtpDb == null) {
            customerOtpDb = new CustomerOtp();
            customerOtpDb.setCustomer(customer);
        }
        customerOtpDb.setOtpMessage(customerOtp.getOtpMessage());
        customerOtpDb.setOtp(customerOtp.getOtp());
        customerOtpDb.setRetries(customerOtp.getRetries());
        customerOtpDb.setCreatedOn(customerOtp.getCreatedOn());
        customerOtpDb.setExpiresOn(customerOtp.getExpiresOn());
        customerOtpRepo.save(customerOtpDb);
    }
}
