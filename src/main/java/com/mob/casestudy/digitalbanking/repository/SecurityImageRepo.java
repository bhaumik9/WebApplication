package com.mob.casestudy.digitalbanking.repository;

import com.mob.casestudy.digitalbanking.entity.SecurityImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityImageRepo extends JpaRepository<SecurityImages,String> {

    SecurityImages findBySecurityImagesName(String name);
}
