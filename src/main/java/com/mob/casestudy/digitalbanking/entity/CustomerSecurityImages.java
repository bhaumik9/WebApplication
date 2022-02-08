package com.mob.casestudy.digitalbanking.entity;

import com.mob.casestudy.digitalbanking.entity.embeddables.CustomerSecurityImagesId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class CustomerSecurityImages {

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
