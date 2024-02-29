package com.example;

import com.example.Exception.RepositoryException;
import com.example.Settings.Settings;
import com.example.lab5.MainController;
import com.example.domain.Pacient;
import com.example.domain.PacientConvert;
import com.example.domain.Programare;
import com.example.domain.ProgramareConvert;
import com.example.repository.*;
import com.example.service.ServicePacient;
import com.example.service.ServiceProgramare;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class MainApplication extends Application {
    static Repository<Pacient> repoPacient = null;
    static Repository<Programare> repoProgramare = null;
    static PacientConvert pacientConvert = new PacientConvert();
    static ProgramareConvert programareConvert = new ProgramareConvert();
    static Settings setari = Settings.getInstance();

    @Override
    public void start(Stage stage) throws IOException, SQLException, RepositoryException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("pacient-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
        stage.setTitle("CLINICA");
        stage.setScene(scene);
        stage.show();


        if (Objects.equals(setari.getRepoType(), "memory")) {
            repoPacient = new Repository<Pacient>();
            repoProgramare = new Repository<Programare>();
        }
            if (Objects.equals(setari.getRepoType(), "text")) {
            /*
            repo_type: text
            repo_file1: /Volumes/Macintosh HD/Desktop/PROGRAMARE/MAP/a4-aluas21/src/main/java/Pacienti.txt
            repo_file2: /Volumes/Macintosh HD/Desktop/PROGRAMARE/MAP/a4-aluas21/src/main/java/Programari.txt
             */
                repoPacient = new TextFileRepository<>(setari.getRepoFile1(), pacientConvert);
                repoProgramare = new TextFileRepository<>(setari.getRepoFile2(), programareConvert);
            }

            if (Objects.equals(setari.getRepoType(), "bin")) {
                repoPacient = new BinaryFileRepository<>(setari.getRepoFile1());
                repoProgramare = new BinaryFileRepository<>(setari.getRepoFile2());
            }

            if (Objects.equals(setari.getRepoType(), "db")) {
                repoPacient = new DBRepository(setari.getRepoFile1());
                repoProgramare = new DBRepository1( repoPacient);
            }


            ServiceProgramare serviceProgramari = new ServiceProgramare(repoProgramare);
            ServicePacient servicePacient = new ServicePacient(repoPacient, repoProgramare);


            MainController pacientController = fxmlLoader.getController();
            pacientController.setService(servicePacient, serviceProgramari);

        }

    public static void main(String[] args) {
        launch();

        if (Objects.equals(setari.getRepoType(), "db")) {
            assert repoPacient != null;
            assert repoPacient instanceof DBRepository;
            ((DBRepository) repoPacient).closeConnection();
            ((DBRepository1) repoProgramare).closeConnection();
        }
    }

}