package com.example.Validation;
import com.example.Exception.RepositoryException;

public class NameValidation {
    public NameValidation(String nume) throws RepositoryException {
        if((nume.equals(null) || nume.length()<2 ) || Number(nume)==true)
            throw new RepositoryException("Invalid name");
    }
    public boolean Number(String nume){
        char number[] = {'0', '1', '2', '3', '4', '5', '6', '7','8','9'};
        for(int i=0;i<9;i++){
            if(nume.contains(String.valueOf(number[i])))
                return true;
        }
        return false;
    }
}
