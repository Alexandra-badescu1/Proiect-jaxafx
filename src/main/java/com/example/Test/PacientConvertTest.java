package com.example.Test;

import com.example.domain.Pacient;
import com.example.domain.PacientConvert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacientConvertTest {
    PacientConvert convert = new PacientConvert();
    @Test
    void ToString() {

        Pacient pacient = new Pacient(1,"cristi","radu",56);
       // System.out.println(convert.ToString(pacient));
        assert convert.ToString(pacient).equals("1,cristi,radu,56");
    }

    @Test
    void fromString() {
        Pacient pacient1 = new Pacient(1,"cristi","radu",56);
        String pacient = "1,cristi,radu,56";
       // System.out.println(convert.FromString(pacient));
        assert convert.FromString(pacient).toString().equals(pacient1.toString());
    }
}