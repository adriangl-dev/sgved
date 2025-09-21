package com.tfm.sgved.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "QUESTIONS")
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

    @Transient
    private @Getter @Setter int respuestasTotales = 0;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nsurvey", insertable = false, updatable = false)
    private Survey survey;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private @Getter @Setter List<Answer> answers;
}