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
@Table(name = "ANSWERS")
@Data
@NoArgsConstructor
public class Answer {
    @Id
    @Column
    private @Getter @Setter int id;
    @Column
    private @Getter @Setter int id_question;
    @Column
    private @Getter @Setter String text;

}
