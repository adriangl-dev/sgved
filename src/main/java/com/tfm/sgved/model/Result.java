package com.tfm.sgved.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="RESULTS")
@Data
@NoArgsConstructor
public class Result {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column
    private @Getter @Setter int question;

    @Column
    private @Getter @Setter int answer;

    @Column
    private @Getter @Setter int survey;
}