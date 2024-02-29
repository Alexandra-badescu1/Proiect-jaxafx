package com.example.Test;

import com.example.domain.IEntitieConvert;
import com.example.domain.Pacient;
import com.example.domain.PacientConvert;
import org.junit.jupiter.api.BeforeEach;
import com.example.repository.TextFileRepository;
import com.example.Exception.RepositoryException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TextFileRepositoryTest {
    public IEntitieConvert<Pacient> ec = new PacientConvert();
    public TextFileRepository<Pacient> repoPacient = new TextFileRepository<Pacient>("src/pacient.txt",ec);

    @org.junit.jupiter.api.Test
    void add() throws SQLException, RepositoryException {
        try {
            Pacient pacient = new Pacient(9, "andra", "stefi", 19);
            repoPacient.Add(pacient);
        } catch (RepositoryException | SQLException e) {
            System.out.println(e.getMessage());
        }
            System.out.println(repoPacient.GetAll().size());
            assert repoPacient.GetAll().size() == 5;
        }

    @org.junit.jupiter.api.Test
    void update() {
        try{
            add();
            Pacient pacient = new Pacient(4,"andra","stefi",19);
            repoPacient.Update(repoPacient.GetById(4),pacient);
        }catch (RepositoryException | SQLException e){
            System.out.println(e.getMessage());

        }
        assert repoPacient.GetById(4).GetNume() == "andra";
    }
    @org.junit.jupiter.api.Test
    void delete() throws SQLException, RepositoryException {
        try{
            add();
            repoPacient.Delete(2);
        }catch (RepositoryException | SQLException e){
            System.out.println(e.getMessage());
        }
        assert repoPacient.GetAll().size() == 5;
    }
}