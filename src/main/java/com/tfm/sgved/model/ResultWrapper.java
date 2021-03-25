package com.tfm.sgved.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Data
public class ResultWrapper {
    ArrayList<Result>  resultados = new ArrayList<Result>();
    public @Getter @Setter String dni;
    public @Getter @Setter String key;

    public ArrayList<Result> getResultados(){
        return this.resultados;
    }
    public void setResultados(ArrayList<Result> resultados){
        this.resultados = resultados;
    }
}
