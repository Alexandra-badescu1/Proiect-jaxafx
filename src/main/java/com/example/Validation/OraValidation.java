package com.example.Validation;
import com.example.Exception.RepositoryException;

public class OraValidation{
    public OraValidation(int ora) throws RepositoryException {

        if(ora<8 && ora >20){
            throw new RepositoryException("invalid hour");
        }
    }
}
