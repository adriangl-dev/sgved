package com.tfm.sgved.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PARTICIPANTS")
@Data
@NoArgsConstructor
public class Participant {
    @Id
    @Column
    private int id;

    @Column(name="NSURVEY")
    private @Getter @Setter int nsurvey;

    @Column
    private @Getter @Setter String dni;

    @Column(name="DATEFILLED")
    private @Getter @Setter Date dateFilled;

    @Column(name="FILLED")
    private @Getter @Setter boolean filled;

    @Column
    private @Getter @Setter String key;

    @Column
    private @Getter @Setter String email;
}