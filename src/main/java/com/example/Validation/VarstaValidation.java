package com.example.Validation;
import com.example.Exception.RepositoryException;
public class VarstaValidation {
    public VarstaValidation(int varsta) throws RepositoryException {
        if(varsta<18 || varsta>100)
            throw new RepositoryException("invalid age");
    }
}
