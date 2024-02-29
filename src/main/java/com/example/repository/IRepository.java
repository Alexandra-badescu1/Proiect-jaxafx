package com.example.repository;
import com.example.Exception.RepositoryException;
import com.example.domain.Entitate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public interface IRepository<T extends Entitate> extends Iterable<T> {
    public void Delete(int id) throws RepositoryException;
    public int Size();
    public void Add(T entity) throws RepositoryException, SQLException;
    public boolean FindById(int id);
    public void Update(T old,T newE) throws RepositoryException;
    public T GetById(int id);
    public T GetAt(int pos);
    public ArrayList<T> GetAll() throws SQLException, RepositoryException;
    public Iterator<T> iterator();
}
