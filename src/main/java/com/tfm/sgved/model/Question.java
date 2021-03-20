package com.tfm.sgved.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTIONS")
@Data
@NoArgsConstructor
public class Question {
    @Id
    @Column
    private @Getter @Setter int id;
    @Column
    private @Getter @Setter int id_survey;
    @Column
    private @Getter @Setter String text;
    @Column
    private @Getter @Setter String question_type;
}
