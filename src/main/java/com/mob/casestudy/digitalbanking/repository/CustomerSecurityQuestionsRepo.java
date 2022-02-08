package com.mob.casestudy.digitalbanking.repository;

import com.mob.casestudy.digitalbanking.entity.CustomerSecurityQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSecurityQuestionsRepo extends JpaRepository<CustomerSecurityQuestions,String> {
}
