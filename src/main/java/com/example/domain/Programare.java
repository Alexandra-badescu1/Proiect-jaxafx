package com.example.domain;
import java.io.Serializable;
import java.time.LocalDate;

public class Programare extends Entitate implements Serializable {
    private Pacient pacient;
    private int ora;
    private LocalDate data;
    private String scopul_programari;
    public Programare(int id){super(id);}
    public Programare(int id,Pacient pacient,int ora,LocalDate data,String scopul_programari){
        this(id);
        this.pacient = pacient;
        this.data = data;
        this.ora = ora;
        this.scopul_programari = scopul_programari;
    }
    public Pacient GetPacient(){
        return pacient;
    }
    public int GetOra(){
        return this.ora;
    }
    public LocalDate GetData(){
        return this.data;
    }
    public String GetScopulProgramari(){
        return this.scopul_programari;
    }
    public void SetPacient(Pacient pacient1){
        this.pacient = pacient1;
    }
    public void SetOra(int ora){
        this.ora = ora;
    }
    public void SetData(LocalDate data){
        this.data = data;
    }
    public void SetScopulProgramari(String ScopulProgramarii){
        this.scopul_programari = ScopulProgramarii;
    }

    @Override
    public String toString() {
        return "Programare{" + "id="+ getId() + '\'' +
                ", pacient=" + pacient +
                ", ora=" + ora +
                ", data='" + data + '\'' +
                ", scopul_programari='" + scopul_programari + '\'' +
                '}';
    }
}
