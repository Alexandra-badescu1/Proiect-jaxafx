package com.example.repository;
import com.example.domain.Pacient;
import com.example.Exception.RepositoryException;
import com.example.domain.Programare;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DBRepository1 extends Repository<Programare> {

    IRepository<Pacient> repoPacienti;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static String JDBC_URL=null; //se salveaza o baza de date cu numele dat

    public DBRepository1( IRepository<Pacient> repoPacienti){
        JDBC_URL = "jdbc:sqlite:dataprogramare.db";
        this.repoPacienti = repoPacienti;
        openConnection();
        createSchema();
        initTables();
        loadData();

    }

    private void loadData() {
        ArrayList<Programare> programari = this.GetAll();
        for(Programare p : programari) {
            try {
                super.Add(p);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Connection conn = null;
    private void openConnection() {
        try {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void createSchema() {
        try {
            try (final Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Programari(id int, idPacient int, date varchar(20), timp int, motiv varchar(40));");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }

    void initTables() {
//        final String[] Programari = new String[]{
//                "1 1 2022/11/21 21:30 racit",
//        };
//
//        try {
//            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Programari VALUES (?, ?, ?,?,?)")) {
//                for (String P : Programari) {
//                    String[] tokens = P.split(" ");
//                    statement.setInt(1, Integer.parseInt(tokens[0]));
//                    statement.setInt(2, Integer.parseInt(tokens[1]));
//                    statement.setString(3, tokens[2]);
//                    statement.setString(4, tokens[3]);
//                    statement.setString(5, tokens[4]);
//                    statement.executeUpdate();
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void Add(Programare programare) {
        try {
            super.Add(programare);
        } catch (RepositoryException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Programari VALUES (?, ?, ?,?,?)")) {
                statement.setInt(1, programare.getId());
                statement.setInt(2, programare.GetPacient().getId());
                statement.setString(3, formatter.format(programare.GetData()));
                statement.setInt(4, programare.GetOra());
                statement.setString(5, programare.GetScopulProgramari());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(int id) {
        try {
            super.Delete(id);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        try {
            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM Programari WHERE id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Programare> GetAll() {
        ArrayList<Programare> programare = new ArrayList<>();
        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from Programari"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    Pacient pacient = new Pacient(rs.getInt("idPacient"));
                    pacient = repoPacienti.GetById(pacient.getId());
                    Programare p = new Programare(rs.getInt("id"),
                                                pacient,
                            Integer.parseInt(rs.getString("timp")),
                            LocalDate.parse(rs.getString("date"), formatter),
                                                rs.getString("motiv"));
                    programare.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return programare;
    }

    @Override
    public void Update(Programare nou, Programare programare) {
        try {
            super.Update(nou,programare);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        try {
            conn.setAutoCommit(false);

            try (PreparedStatement updateProgramare = conn.prepareStatement("UPDATE Programari SET id = ?, idPacient = ?, date = ?, time = ?, motiv = ? WHERE id = ?")){
                updateProgramare.setInt(1, programare.getId());
                updateProgramare.setInt(2, programare.GetPacient().getId());
                updateProgramare.setString(3, formatter.format(programare.GetData()));
                updateProgramare.setInt(4, programare.GetOra());
                updateProgramare.setString(5, programare.GetScopulProgramari());
                updateProgramare.executeUpdate();
                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



}
