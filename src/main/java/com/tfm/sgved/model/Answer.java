package com.tfm.sgved.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ANSWERS")
@Data
@NoArgsConstructor
public class Answer {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private @Getter @Setter int id;

    @Column
    private @Getter @Setter int nquestion;

    @Column
    private @Getter @Setter String text;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nquestion", insertable = false, updatable = false)
    private Question question;
}