package com.example.Validation;
import com.example.Exception.RepositoryException;
public class DataValidation {
    public DataValidation(String data) throws RepositoryException{
        if(!Number(data))
            throw new RepositoryException("INVALID DATE");
    }
    public boolean Number(String nume) {
        char number[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (int i = 0; i < 9; i++) {
            if (!nume.contains(String.valueOf(number[i])))
                return false;
        }
        return true;
    }
}
