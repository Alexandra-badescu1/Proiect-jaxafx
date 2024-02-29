package com.example.domain.dto;

import com.example.domain.Pacient;

public class PacientZileProg {
    private Pacient pacient;
    private int id;
    private String name;
    private String surname;
    private int age;
    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
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

    public void setDaysAfterLastProg(int daysAfterLastProg) {
        this.daysAfterLastProg = daysAfterLastProg;
    }

    private int daysAfterLastProg;

    public PacientZileProg(Pacient p, int daysAfterLastProg){
        pacient = p;
        this.daysAfterLastProg = daysAfterLastProg;
        id = pacient.getId();
        name = pacient.GetNume();
        age =  pacient.GetVarsta();
    }


    public Pacient getPacient() {
        return pacient;
    }

    public int getId() {
        return id;
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

    public int getDays() {
        return daysAfterLastProg;
    }


}