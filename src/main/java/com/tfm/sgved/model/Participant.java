package com.tfm.sgved.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "PARTICIPANTS")
@Data
@NoArgsConstructor
public class Participant {
    @Id
    @Column
    private int id;
    @Column
    private @Getter @Setter int survey;
    @Column
    private @Getter @Setter String dni;
    @Column
    private @Getter @Setter Date date_filled;
    @Column
    private @Getter @Setter String done;
    @Column
    private @Getter @Setter String key;
}
