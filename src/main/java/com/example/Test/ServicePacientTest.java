package com.example.Test;

import com.example.domain.Pacient;
import com.example.domain.Programare;
import com.example.repository.Repository;
import com.example.Exception.RepositoryException;
import com.example.service.ServicePacient;
import com.example.service.ServiceProgramare;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ServicePacientTest {
    public Repository<Pacient> pacients = new Repository<Pacient>();
    public Repository<Programare> programares = new Repository<Programare>();
    public ServicePacient pacient = new ServicePacient(pacients,programares);
    public ServiceProgramare programare = new ServiceProgramare(programares);
    @org.junit.jupiter.api.Test
    void add() throws RepositoryException, SQLException {
        try {
            pacient.Add(1, "andrei", "cosmin", 34);
        }catch (RepositoryException a){
            System.out.println(a.getMessage());
        }
        try {
            programare.Add(1,pacient.GetAll().get(0), 12, LocalDate.parse("2023-12-12"),"durere");
        }catch (RepositoryException e){
            throw new RepositoryException("Bad adding",e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assert pacient.GetAll().size() == 1;
        assert programare.GetAll().size() == 1;
    }

    @org.junit.jupiter.api.Test
    void update() throws RepositoryException, SQLException {
        try{
            pacient.Update(1,"CRISTI","CATI",16);
        }catch (RepositoryException e){
            throw new RepositoryException("Bad adding",e);
        }
        try{
            programare.Update(1,pacient.GetAll().get(0),15, LocalDate.parse("2023-12-12"),"cordonul" );
        }catch (RepositoryException e){
            System.out.println(e.getMessage());
        }
        assert pacient.GetAll().get(0).GetNume()=="CRISTI";
        assert programare.GetAll().get(0).GetOra() == 15;
    }

    @org.junit.jupiter.api.Test
    void getAll() throws SQLException, RepositoryException {

        assert pacient.GetAll().isEmpty();
    }

    @org.junit.jupiter.api.Test
    void delete() throws SQLException, RepositoryException {
        try{
            pacient.Delete(1);
        }catch (RepositoryException e){
            System.out.println(e.getMessage());
        }
        try{
            programare.Delete(1);
        }catch (RepositoryException e){
            System.out.println(e.getMessage());
        }
        assert pacient.GetAll().size() == 0;
        assert programare.GetAll().size() == 0;
    }
}