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
@Table (name = "USERS")

@Data

@NoArgsConstructor
public class User {

    @Column
    private @Getter @Setter String dni;
    @Id
    @Column
    private @Getter @Setter String email;
    @Column
    private @Getter @Setter String password;
    @Column
    private @Getter @Setter String rol;
}