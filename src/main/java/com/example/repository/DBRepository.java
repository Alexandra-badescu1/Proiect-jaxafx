package com.example.repository;

import com.example.domain.Pacient;
import com.example.Exception.RepositoryException;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

public class DBRepository extends Repository<Pacient> {

        private final String dbLocation;

        private Connection conn = null;

        public DBRepository(String dbLocation) throws RepositoryException, SQLException {
            this.dbLocation = "jdbc:sqlite:" + dbLocation;
            initDbConnection();
            createSchema();
            loadData();
        }

    private void loadData() {
        ArrayList<Pacient> pacients = this.GetAll();
        for(Pacient p : pacients) {
            try {
                super.Add(p);
            } catch (RepositoryException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
        private void initDbConnection() throws RepositoryException {
            try {
                // with DataSource
                SQLiteDataSource ds = new SQLiteDataSource();
                ds.setUrl(dbLocation);
                if (conn == null || conn.isClosed())
                    conn = ds.getConnection();
            } catch (SQLException e) {
                throw new RepositoryException("Error creating DB connection", e);
            }
        }

        void createSchema() throws RepositoryException {
            try {
                try (final Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS pacient(id int,nume char,prenume char,varsta int);");
                }
            } catch (SQLException e) {
                throw new RepositoryException("Error creating DB schema", e);
            }
        }

    @Override
    public void Add(Pacient entity) throws RepositoryException, SQLException {
        super.Add(entity);
        try (PreparedStatement statement = conn.prepareStatement("INSERT INTO pacient VALUES (?,?,?,?)")) {
            statement.setInt(1,entity.GetId());
            statement.setString(2, entity.GetNume());
            statement.setString(3, entity.GetPrenume());
            statement.setInt(4,entity.GetVarsta());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Pacient> GetAll() {
            ArrayList<Pacient> pacients = new ArrayList<Pacient>();
        try(PreparedStatement statement = conn.prepareStatement("SELECT * from pacient"); ResultSet rs = statement.executeQuery(); ){
            while(rs.next()){
                Pacient p = new Pacient(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
                pacients.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pacients;
    }

    @Override
    public void Update(Pacient old, Pacient newE) throws RepositoryException {
        super.Update(old, newE);

        try (PreparedStatement statement = conn.prepareStatement("UPDATE pacient set  id = ? , nume=?,prenume=?,varsta=?")) {
            statement.setInt(1,newE.GetId());
            statement.setString(2, newE.GetNume());
            statement.setString(3, newE.GetPrenume());
            statement.setInt(4,newE.GetVarsta());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Delete(int id) throws RepositoryException {
            try {
                super.Delete(id);
            }catch (RepositoryException exception){
                throw new RepositoryException(exception.getMessage());
            }
        try {
            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM pacient WHERE id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
    }
}

