package com.example.repository;

import com.example.domain.Entitate;
import com.example.Exception.RepositoryException;
import com.example.domain.Programare;

import java.sql.SQLException;
import java.util.ArrayList;

public class Repository<T extends Entitate> extends AbstractRepository<T> {

    @Override
    public void Add(T entity) throws RepositoryException, SQLException {
        if (!FindById(entity.getId()))
            data.add(entity);
        else throw new RepositoryException("double id");
    }

    @Override
    public int Size() {
        return data.size();
    }

    @Override
    public void Delete(int id) throws RepositoryException {
        if (FindById(id)) {
            for (int i = 0; i < data.size(); i++)
                if (data.get(i).getId() == id)
                    data.remove(data.get(i));
        } else throw new RepositoryException("id invalid");
    }


    @Override
    public void Update(T old, T newE) throws RepositoryException {
        if (!FindById(old.getId()))
            throw new RuntimeException("Error: ID-ul nu a fost gasit");
        for (int i = 0; i < data.size(); i++)
            if (data.get(i).getId() == old.getId())
                data.set(i, newE);
    }

    @Override
    public boolean FindById(int id) {
        for (T entity : data) {
            if (entity.getId() == id)
                return true;
        }
        return false;
    }

    @Override
    public T GetById(int id) {
        for (T entity : data) {
            if (entity.getId() == id)
                return entity;
        }
        return null;
    }

    @Override
    public T GetAt(int pos) {
        return data.get(pos);
    }

    @Override
    public ArrayList<T> GetAll() throws SQLException, RepositoryException {
        return new ArrayList<>(data);
    }

}
