package com.tfm.sgved.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SURVEYS")
@Data
@NoArgsConstructor
public class Survey {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private @Getter @Setter int id;
    @Column
    private @Getter @Setter String title;
    @Column
    private @Getter @Setter String description;
    @Column
    private @Getter @Setter int participants_number;
}