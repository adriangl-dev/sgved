package com.tfm.sgved.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Result {
    private @Getter @Setter int question;
    private @Getter @Setter int answer;
    private @Getter @Setter int survey;
}