package com.mob.casestudy.digitalbanking.dto;

import com.mob.casestudy.digitalbanking.entity.Customer;
import com.mob.casestudy.digitalbanking.entity.embeddables.CustomerSecurityImagesId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

public class CustomerSecurityImagesDto {

    @EmbeddedId
    CustomerSecurityImagesId customerSecurityImagesId = new CustomerSecurityImagesId();

    @OneToOne
    @MapsId("customerId")
    Customer customer;

    @Column(name = "security_image_caption")
    String securityImageCaption;

    @Column(name = "created_on")
    LocalDateTime createdOn;
}
