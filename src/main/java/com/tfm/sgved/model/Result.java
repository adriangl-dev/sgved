package com.tfm.sgved.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Result {
    private @Getter @Setter int id_question;
    private @Getter @Setter int id_answer;
    private @Getter @Setter String dni;
    private @Getter @Setter int id_survey;
}
