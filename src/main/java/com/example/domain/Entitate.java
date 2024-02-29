package com.example.domain;

import java.io.Serializable;

public class Entitate implements Serializable {
    private int id;

    public Entitate(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
