package com.mob.casestudy.digitalbanking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSecurityQuestionsDto {
    String securityQuestionId;
    String securityQuestionText;
    String securityQuestionAnswer;
}
