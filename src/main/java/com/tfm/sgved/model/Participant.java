package com.tfm.sgved.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "PARTICIPANTS")
@Data
@NoArgsConstructor
public class Participant {
    @Id
    @Column
    private @Getter @Setter int id_survey;
    @Column
    private @Getter @Setter String dni;
    @Column
    private @Getter @Setter Date date_filled;
    @Column
    private @Getter @Setter String done;
}
