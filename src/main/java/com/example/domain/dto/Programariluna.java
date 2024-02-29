package com.example.domain.dto;
public class Programariluna {
    private int luna;
    private int nrProg;

    public Programariluna(int luna, int nrProg){
        this.luna = luna;
        this.nrProg = nrProg;
    }

    public int getLuna(){
        return luna;
    }

    public int getNrProg(){
        return nrProg;
    }

    public void setLuna(int luna){
        this.luna = luna;
    }

    public void setNrProg(int nrProg){
        this.nrProg = nrProg;
    }

}
