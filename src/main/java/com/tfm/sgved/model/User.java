package com.tfm.sgved.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table

@Data

@NoArgsConstructor
public class User {
    @Id
    @Column
    private @Getter @Setter String dni;
    @Column
    private @Getter @Setter String email;
    @Column
    private @Getter @Setter String password;
    @Column
    private @Getter @Setter String rol;
}