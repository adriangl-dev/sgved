package com.tfm.sgved.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SURVEYS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Survey {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private @Getter @Setter int id;
    @Column
    private @Getter @Setter String title;
    @Column
    private @Getter @Setter String description;
    @Column
    private @Getter @Setter int participants_number;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "survey")
    private @Getter @Setter List<Question> questions;
}