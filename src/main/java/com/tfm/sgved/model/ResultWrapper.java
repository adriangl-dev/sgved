package com.tfm.sgved.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ResultWrapper {
    ArrayList<Result>  resultados = new ArrayList<Result>();

    public ArrayList<Result> getResultados(){
        return this.resultados;
    }
    public void setResultados(ArrayList<Result> resultados){
        this.resultados = resultados;
    }
}
