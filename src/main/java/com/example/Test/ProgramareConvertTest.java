package com.example.Test;

import com.example.domain.Pacient;
import com.example.domain.PacientConvert;
import com.example.domain.Programare;
import com.example.domain.ProgramareConvert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProgramareConvertTest {
    ProgramareConvert convert = new ProgramareConvert();
    @Test
    void ToString() {
        Pacient pacient1 = new Pacient(1,"cristi","radu",56);
        Programare programare = new Programare(1,pacient1,12, LocalDate.parse("2023-12-12"),"codrii");
        //System.out.println(convert.ToString(programare));
        assert convert.ToString(programare).equals("1,1,cristi,radu,56,12,21-01-2023,codrii");
    }

    @Test
    void fromString() {
        Pacient pacient1 = new Pacient(1,"cristi","radu",56);
        Programare programare = new Programare(1,pacient1,12, LocalDate.parse("2023-12-12"),"codrii");
        String programare1 ="1,1,cristi,radu,56,12,21-01-2023,codrii";
        convert.FromString(programare1).toString().equals(programare.toString());
    }
}