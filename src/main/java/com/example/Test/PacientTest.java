package com.example.Test;

import com.example.domain.Entitate;
import com.example.domain.Pacient;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacientTest extends Assert {

    @org.junit.jupiter.api.Test
    void setNume() {
        Pacient pacient = new Pacient(1, "cristi", "radu", 15);
        pacient.SetNume("radu");
        assert pacient.GetNume() == "radu";
    }

    @org.junit.jupiter.api.Test
    void setPrenume() {
        Pacient pacient = new Pacient(1, "cristi", "radu", 15);
        pacient.SetPrenume("cristi");
        assert pacient.GetPrenume() == "cristi";
    }

    @org.junit.jupiter.api.Test
    void setVarsta() {
        Pacient pacient = new Pacient(1, "cristi", "radu", 15);
        pacient.SetVarsta(19);
        assert pacient.GetVarsta() == 19;
    }

    @org.junit.jupiter.api.Test
    void getId() {
        Pacient pacient = new Pacient(1, "cristi", "radu", 15);
        assert pacient.GetId()==1;
    }

    @org.junit.jupiter.api.Test
    void getNume() {
        Pacient pacient = new Pacient(1, "cristi", "radu", 15);
        assert pacient.GetNume() == "cristi";
    }

    @org.junit.jupiter.api.Test
    void getPrenume() {
        Pacient pacient = new Pacient(1, "cristi", "radu", 15);
        assert pacient.GetPrenume() == "radu";
    }

    @org.junit.jupiter.api.Test
    void getVarsta() {
        Pacient pacient = new Pacient(1, "cristi", "radu", 15);
        assert pacient.GetVarsta() == 15;
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Pacient pacient = new Pacient(1, "cristi", "radu", 15);
        //System.out.println(pacient.toString().length());
        assert pacient.toString().length() == 58;
    }
}