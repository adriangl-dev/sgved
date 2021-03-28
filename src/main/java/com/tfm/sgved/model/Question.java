package com.tfm.sgved.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "QUESTIONS")
@Data
@NoArgsConstructor
public class Question {
    @Id
    @Column (name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private @Getter @Setter int id;

    @Column(name="NSURVEY")
    private @Getter @Setter int nsurvey;

    @Column
    private @Getter @Setter String text;

    @Column
    private @Getter @Setter String type;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nsurvey", insertable = false, updatable = false)
    private Survey survey;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private @Getter @Setter List<Answer> answers;
}