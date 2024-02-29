package com.example;

import com.example.Settings.Settings;
import com.example.UI.Interfata;
import com.example.domain.*;
import com.example.repository.*;
import com.example.service.*;
import com.example.Exception.*;

import java.sql.SQLException;
import java.util.Objects;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
 public class Main {

    public static void main(String[] args) throws IOException, RepositoryException, SQLException {

       // IRepository<Pacient> repo ;
        //IRepository<Programare> repoP ;
        IRepository<Pacient> repoPacient = null;
        IRepository<Programare> programareRepository = null;
        IEntitieConvert<Pacient> ec = new PacientConvert();
        IEntitieConvert<Programare> ECP = new ProgramareConvert();
        Settings setari = Settings.getInstance();
        if (Objects.equals(setari.getRepoType(), "memory")) {
            repoPacient = new Repository<Pacient>();
            programareRepository = new Repository<Programare>();
        }

        if (Objects.equals(setari.getRepoType(), "text")) {
            repoPacient = new TextFileRepository<Pacient>(setari.getRepoFile1(), ec);
            programareRepository = new TextFileRepository<Programare>(setari.getRepoFile2(),ECP);
            System.out.println("ok");
        }
        if (Objects.equals(setari.getRepoType(), "binary")){
            repoPacient = new BinaryFileRepository<Pacient>(setari.getRepoFile1());
            programareRepository = new BinaryFileRepository<Programare>(setari.getRepoFile2());

        }
        if(Objects.equals(setari.getRepoType(), "database")){
            System.out.println("ok2");
            repoPacient = new DBRepository(setari.getRepoFile1());
            programareRepository = new DBRepository1( repoPacient);

        }

        //BinaryFileRepository<Pacient> repoPacient = new BinaryFileRepository<>("src/binarypacient.bin");
        //BinaryFileRepository<Programare> programareRepository = new BinaryFileRepository<>("src/binaryprogramare.bin");
        ServiceProgramare serviceProgramare = new ServiceProgramare(programareRepository);
        ServicePacient servicePacient = new ServicePacient(repoPacient,programareRepository);
        Interfata ui = new Interfata(servicePacient,serviceProgramare);
        ui.Meniu();

    }
}