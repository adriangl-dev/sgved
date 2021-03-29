package com.tfm.sgved.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import javax.persistence.*;

@Entity
@Table(name = "ANSWERS")
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

    @Transient
    private @Getter @Setter String total = "0";

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nquestion", insertable = false, updatable = false)
    private Question question;
}