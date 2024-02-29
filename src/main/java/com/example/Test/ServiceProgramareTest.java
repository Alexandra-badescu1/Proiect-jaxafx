package com.example.Test;

import com.example.domain.Pacient;
import com.example.domain.Programare;
import com.example.repository.Repository;
import com.example.service.ServiceProgramare;
import com.example.Exception.RepositoryException;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ServiceProgramareTest {
    public Repository<Programare> programares = new Repository<Programare>();
    public ServiceProgramare programare = new ServiceProgramare(programares);
    @org.junit.jupiter.api.Test
    void add() throws SQLException, RepositoryException {
    try{
        Pacient PACIENT = new Pacient(1,"radu","mihnea",23);
        programare.Add(1,PACIENT,23, LocalDate.parse("2023-12-12"),"Carie");
        programare.Add(2,PACIENT,23, LocalDate.parse("2023-12-12"),"asta");
        }catch (RepositoryException e){
        System.out.println(e.getMessage());
    }
    assert programare.GetAll().size() == 2;
    }

    @org.junit.jupiter.api.Test
    void getAll() throws SQLException, RepositoryException {
        add();
        System.out.println(programare.GetAll().size());
        assert programare.GetAll().size() == 2;
    }

    @org.junit.jupiter.api.Test
    void delete() throws SQLException, RepositoryException {
        add();
        try{
            programare.Delete(2);
        }catch (RepositoryException e){
            System.out.println(e.getMessage());
        }
    }


    @org.junit.jupiter.api.Test
    void update() throws SQLException, RepositoryException {
        add();
        try{
            Pacient PACIENT = new Pacient(1,"radu","mihnea",23);
            programare.Update(1,PACIENT,12, LocalDate.parse("2023-12-12"),"uitr");
        }catch (RepositoryException e){
            System.out.println(e.getMessage());
        }
        assert programare.GetAt(1).GetNume() == "radu";
    }

    @org.junit.jupiter.api.Test
    void getAt() throws SQLException, RepositoryException {
        add();
       assert  programare.GetAt(1).GetId() == 1;
    }
}