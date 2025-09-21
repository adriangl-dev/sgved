package com.tfm.sgved.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table (name = "USERS")

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