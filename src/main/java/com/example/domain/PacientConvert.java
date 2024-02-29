package com.example.domain;

public class PacientConvert implements IEntitieConvert<Pacient> {
    @Override
    public String ToString(Pacient elem){
    return elem.getId()+ "," + elem.GetNume() + "," + elem.GetPrenume() + "," + elem.GetVarsta();
    }
    @Override
    public Pacient FromString(String line){
        String[] tokens = line.split(",");
        return new Pacient(Integer.parseInt(tokens[0]),tokens[1],tokens[2],Integer.parseInt(tokens[3]));
    }
}
