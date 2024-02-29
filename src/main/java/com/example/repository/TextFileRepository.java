package com.example.repository;

import com.example.domain.Entitate;
import com.example.domain.IEntitieConvert;
import com.example.Exception.RepositoryException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class TextFileRepository<T extends Entitate> extends Repository<T>{
    private final String fileName;
    private IEntitieConvert<T> convertor;

    public TextFileRepository(String fileName, IEntitieConvert<T> converter) {
        this.fileName = fileName;
        this.convertor = converter;

        try {
            loadFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadFile() throws IOException {
        // delete whatever is in the repo's list
        data.clear();

        // BufferedReader - reads data ahead into a buffer :)
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null && !line.isEmpty()) {
                data.add(convertor.FromString(line));
                line = br.readLine();
            }
        }
    }
    @Override
    public void Add(T o) throws RepositoryException, SQLException {
        super.Add(o);
        try {
            saveFile();
        } catch (IOException e) {
            throw new RepositoryException("Error saving object", e);
        }
    }

    private void saveFile() throws IOException {
        // TODO File is rewritten at each modification :'(
        try (FileWriter fw = new FileWriter(fileName)) {
            for (T object : data) {
                fw.write(convertor.ToString(object));
                fw.write("\r\n");
            }
        }
    }

    @Override
    public void Update(T old, T newE) throws RepositoryException {
        super.Update(old, newE);
        try {
            saveFile();
        } catch (IOException e) {
            throw new RepositoryException("Error saving object", e);
        }
    }

    @Override
    public void Delete(int id) throws RepositoryException {
        super.Delete(id);
        try {
            saveFile();
        } catch (IOException e) {
            throw new RepositoryException("Error saving object", e);
        }
    }
}
