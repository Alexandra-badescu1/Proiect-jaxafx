package com.example.repository;

import com.example.domain.Entitate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractRepository<T extends Entitate> implements IRepository<T> {

    protected List<T> data = new ArrayList<>();
@Override
public Iterator<T> iterator() {
        // returnam un iterator pe un shallow copy :) al campului data
        return new ArrayList<T>(data).iterator();
//        return data.iterator();
        }
}
