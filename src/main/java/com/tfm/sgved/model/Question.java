package com.tfm.sgved.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "QUESTIONS")
@Data
@NoArgsConstructor
public class Question {
    @Id
    @Column (name = "id")
    private @Getter @Setter int id;
    @Column
    private @Getter @Setter int id_survey;
    @Column
    private @Getter @Setter String text;
    @Column
    private @Getter @Setter String question_type;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_survey", insertable = false, updatable = false)
    private Survey survey;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<Answer> answers;
}
