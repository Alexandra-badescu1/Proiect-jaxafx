package com.example.Test;

import com.example.domain.Pacient;
import org.junit.jupiter.api.Test;
import com.example.repository.AbstractRepository;
import com.example.Exception.RepositoryException;
import com.example.repository.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AbstractRepositoryTest {

    public Repository<Pacient> abstractRepository = new Repository<Pacient>();

    @Test
    void iterator() throws RepositoryException {
        try {
            abstractRepository.Add(new Pacient(1, "cristi", "radu", 15));
            abstractRepository.Add(new Pacient(2, "cosmin", "gabi", 21));
            abstractRepository.Add(new Pacient(3, "etere", "etori", 32));
            abstractRepository.Add(new Pacient(4, "iuli", "romeo", 23));
            abstractRepository.Add(new Pacient(5, "sandie", "orto", 45));
        }catch (RepositoryException e){
           //new assertThrows(new RepositoryException("assert false",e));
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //System.out.println( abstractRepository.iterator().next());
        assert abstractRepository.iterator().next().toString().equals("Pacient{id = 1', nume='cristi', prenume='radu', varsta=15}");

    }
}