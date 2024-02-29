package com.example.Test;

import com.example.domain.Pacient;
import com.example.repository.Repository;
import com.example.Exception.RepositoryException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    @org.junit.jupiter.api.Test
    void add() throws RepositoryException, SQLException {
        Repository<Pacient> pacient = new Repository<Pacient>();
        try {

            pacient.Add(new Pacient(1, "cristi", "radu", 15));
            pacient.Add(new Pacient(2, "cosmin", "gabi", 21));
            pacient.Add(new Pacient(3, "etere", "etori", 32));
            pacient.Add(new Pacient(4, "iuli", "romeo", 23));
            pacient.Add(new Pacient(5, "sandie", "orto", 45));

        }catch (RepositoryException | SQLException e)
        {
            throw new RepositoryException("pacients could not be add",e);
        }
        assert pacient.GetAll().size() == 5;
    }
    @org.junit.jupiter.api.Test
    void size() {
        Repository<Pacient> pacient = new Repository<Pacient>();
        try {

            pacient.Add(new Pacient(1, "cristi", "radu", 15));
            pacient.Add(new Pacient(2, "cosmin", "gabi", 21));
            pacient.Add(new Pacient(3, "etere", "etori", 32));
            pacient.Add(new Pacient(4, "iuli", "romeo", 23));
            pacient.Add(new Pacient(5, "sandie", "orto", 45));

        }catch (RepositoryException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        assert pacient.Size() == 5;

    }

    @org.junit.jupiter.api.Test
    void delete() throws SQLException, RepositoryException {
        Repository<Pacient> pacient = new Repository<Pacient>();
        try {

            pacient.Add(new Pacient(1, "cristi", "radu", 15));
            pacient.Add(new Pacient(2, "cosmin", "gabi", 21));
            pacient.Add(new Pacient(3, "etere", "etori", 32));
            pacient.Add(new Pacient(4, "iuli", "romeo", 23));
            pacient.Add(new Pacient(5, "sandie", "orto", 45));
            pacient.Delete(3);

        }catch (RepositoryException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        assert pacient.GetAll().size() == 4;

    }

    @org.junit.jupiter.api.Test
    void update() {
        Repository<Pacient> pacient = new Repository<Pacient>();
        try {

            pacient.Add(new Pacient(1, "cristi", "radu", 15));
            pacient.Add(new Pacient(2, "cosmin", "gabi", 21));
            pacient.Add(new Pacient(3, "etere", "etori", 32));
            pacient.Add(new Pacient(4, "iuli", "romeo", 23));
            pacient.Add(new Pacient(5, "sandie", "orto", 45));
            pacient.Update(pacient.GetById(1),new Pacient(1,"manole","raduteanu",18));

        }catch (RepositoryException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        assert pacient.GetById(1).GetNume() == "manole";
    }

    @org.junit.jupiter.api.Test
    void findById() {
        Repository<Pacient> pacient = new Repository<Pacient>();
        try {

            pacient.Add(new Pacient(1, "cristi", "radu", 15));
            pacient.Add(new Pacient(2, "cosmin", "gabi", 21));
            pacient.Add(new Pacient(3, "etere", "etori", 32));
            pacient.Add(new Pacient(4, "iuli", "romeo", 23));
            pacient.Add(new Pacient(5, "sandie", "orto", 45));

        }catch (RepositoryException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        assert pacient.FindById(3) == true;
    }

    @org.junit.jupiter.api.Test
    void getById() {
        Repository<Pacient> pacient = new Repository<Pacient>();
        try {

            pacient.Add(new Pacient(1, "cristi", "radu", 15));
            pacient.Add(new Pacient(2, "cosmin", "gabi", 21));
            pacient.Add(new Pacient(3, "etere", "etori", 32));
            pacient.Add(new Pacient(4, "iuli", "romeo", 23));
            pacient.Add(new Pacient(5, "sandie", "orto", 45));

        }catch (RepositoryException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        assert pacient.GetById(3).GetPrenume().equals("etori");
    }

    @org.junit.jupiter.api.Test
    void getAt() {
        Repository<Pacient> pacient = new Repository<Pacient>();
        try {

            pacient.Add(new Pacient(1, "cristi", "radu", 15));
            pacient.Add(new Pacient(2, "cosmin", "gabi", 21));
            pacient.Add(new Pacient(3, "etere", "etori", 32));
            pacient.Add(new Pacient(4, "iuli", "romeo", 23));
            pacient.Add(new Pacient(5, "sandie", "orto", 45));

        }catch (RepositoryException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        assert pacient.GetAt(1).GetId() == 2;
    }

    @org.junit.jupiter.api.Test
    void getAll() throws SQLException, RepositoryException {
        Repository<Pacient> pacient = new Repository<Pacient>();
        try {

            pacient.Add(new Pacient(1, "cristi", "radu", 15));
            pacient.Add(new Pacient(2, "cosmin", "gabi", 21));
            pacient.Add(new Pacient(3, "etere", "etori", 32));
            pacient.Add(new Pacient(4, "iuli", "romeo", 23));
            pacient.Add(new Pacient(5, "sandie", "orto", 45));

        }catch (RepositoryException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        assert pacient.GetAll().size() == 5;
    }
}