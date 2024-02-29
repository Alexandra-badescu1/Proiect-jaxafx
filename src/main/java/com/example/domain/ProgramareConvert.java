package com.example.domain;

import com.example.domain.PacientConvert;

import java.time.LocalDate;

public class ProgramareConvert implements IEntitieConvert<Programare>{
    @Override
    public String ToString(Programare elem){
        PacientConvert pacientConvert = new PacientConvert();
        return elem.getId()+","+pacientConvert.ToString(elem.GetPacient()) + "," + elem.GetOra() +","+elem.GetData()+ ","+ elem.GetScopulProgramari();
    }
    @Override
    public Programare FromString(String line){
        String[] tokens = line.split(",");
        Pacient pacient= new Pacient(Integer.parseInt(tokens[1]),tokens[2],tokens[3],Integer.parseInt(tokens[4]));
        return new Programare(Integer.parseInt(tokens[0]),pacient,Integer.parseInt(tokens[5]), LocalDate.parse(tokens[6]),tokens[7]);
    }
}
