package com.example.domain.dto;

import com.example.domain.Pacient;

public class PacientNrProg {
    private Pacient pacient;

    private int id;
    private String name;
    private String surname;
    private int age;
    private int nrProg;

    public PacientNrProg(Pacient pacient, int nrProg) {
        this.pacient = pacient;
        this.nrProg = nrProg;
        id = pacient.getId();
        name = pacient.GetNume();
        surname = pacient.GetPrenume();
        age = pacient.GetVarsta();
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public int getNrProg() {
        return nrProg;
    }

    public String getName() {
        return name;
    }


    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public void setNrProg(int nrProg) {
        this.nrProg = nrProg;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }
}