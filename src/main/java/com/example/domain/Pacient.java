package com.example.domain;

import java.io.Serializable;

public class Pacient extends Entitate implements Serializable {
    private String nume;
    private String prenume;
    private int varsta;
    public Pacient(int id){super(id);}
    public Pacient(int id, String nume, String prenume, int varsta){
        this(id);
        this.nume = nume;
        this.prenume = prenume;
        this.varsta= varsta;
    }

    public void SetNume(String nume){
        this.nume = nume;
    }
    public void SetPrenume(String prenume){
        this.prenume = prenume;
    }
    public void SetVarsta(int varsta){
        this.varsta = varsta;
    }
    public int GetId(){
        return getId();
    }
    public String GetNume(){
        return this.nume;
    }
    public String GetPrenume(){
        return this.prenume;
    }
    public int GetVarsta(){
        return this.varsta;
    }

    @Override
    public String toString() {
        return "Pacient{" + "id = "+ getId()+ '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", varsta=" + varsta +
                '}';
    }
}
