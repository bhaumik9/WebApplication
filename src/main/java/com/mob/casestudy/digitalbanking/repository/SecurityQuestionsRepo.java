package com.mob.casestudy.digitalbanking.repository;

import com.mob.casestudy.digitalbanking.entity.SecurityQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityQuestionsRepo extends JpaRepository<SecurityQuestion,String> {
}
