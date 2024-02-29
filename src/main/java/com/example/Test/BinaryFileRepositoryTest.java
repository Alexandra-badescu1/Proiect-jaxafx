package com.example.Test;

import com.example.domain.Pacient;
import com.example.domain.Programare;
import com.example.repository.BinaryFileRepository;
import com.example.Exception.RepositoryException;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BinaryFileRepositoryTest {

    public BinaryFileRepository<Pacient> repositoryTest = new BinaryFileRepository("src/binarypacient.bin");
    public BinaryFileRepository<Programare> repotest = new BinaryFileRepository("src/binaryprogramare.bin");
    @org.junit.jupiter.api.Test
    void add() {
           var current= repositoryTest.Size();
            try{
                repositoryTest.Add(new Pacient(10,"alin","alus",19));
                repotest.Add(new Programare(7,repositoryTest.GetById(10),12, LocalDate.parse("2023-12-12"),"curatenie"));
            }catch (RepositoryException repo){
                System.out.println(repo.getMessage());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        assert repositoryTest.Size() == current+1;
            assert repotest.Size() == 4;
    }

    @org.junit.jupiter.api.Test
    void delete() throws SQLException, RepositoryException {
        var current= repositoryTest.Size();
        try {

            repositoryTest.Delete(10);
            repotest.Delete(7);
        }catch (RepositoryException e){
            System.out.println(e.getMessage());
        }
        assert repotest.GetAll().size() == current+1;
        assert repositoryTest.GetAll().size() == current+1;
    }

    @org.junit.jupiter.api.Test
    void update() {
        add();
        try {
            repositoryTest.Update(repositoryTest.GetById(1),new Pacient(1,"andra","stefi",19));
            repotest.Update(repotest.GetById(7), new Programare(7,repositoryTest.GetById(1),12, LocalDate.parse("2023-12-12"),"foame"));
        }catch (RepositoryException e){
            System.out.println(e.getMessage());
        }
        assert repotest.GetById(7).GetOra() == 12;
        assert repositoryTest.GetById(1).GetNume() == "andra";
    }
}