package com.tfm.sgved.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Entity
@Table(name = "PARTICIPANTS")
@Data
@NoArgsConstructor
public class Participant {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    public String generateSafeToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String token = encoder.encodeToString(bytes);
        return token;
    }
}