package com.wiseconsumo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Abastecimento {
    private double quilometragemAtual;
    private double litrosAbastecidos;
    private BigDecimal valorGasto;
    private LocalDate data;


    public Abastecimento(double quilometragemAtual, double litrosAbastecidos, BigDecimal valorGasto, LocalDate data ){
        this.quilometragemAtual = quilometragemAtual;
        this.litrosAbastecidos = litrosAbastecidos;
        this.valorGasto = valorGasto;
        this.data = data;
    }

    public double getQuilometragemAtual(){
        return quilometragemAtual;
    }

    public double getLitrosAbastecidos(){
        return litrosAbastecidos;
    }

    public BigDecimal getValorGasto(){
        return valorGasto;
    }

    public LocalDate getData(){
        return data;
    }

    public void setQuilometragemAtual(double quilometragemAtual){
        this.quilometragemAtual = quilometragemAtual;
    }

    public void setLitrosAbastecidos(double litrosAbastecidos){
        this.litrosAbastecidos = litrosAbastecidos;
    }
    public void setValorGasto(BigDecimal valorGasto){
        this.valorGasto = valorGasto;
    }
    public void setData(LocalDate data){
        this.data = data;
    }


    //Metodos

    public void mostrarInfo(){
        System.out.println("Quilometragem atual: "+getQuilometragemAtual());
        System.out.println("Valor gasto: R$"+getValorGasto());
        System.out.println("Quantidade de litros abastecidos: "+getLitrosAbastecidos()+" l");
        System.out.println("Data do abastecimento: "+getData());
    }


}


