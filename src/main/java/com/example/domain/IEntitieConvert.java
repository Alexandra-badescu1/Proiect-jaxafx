package com.example.domain;

public interface IEntitieConvert<T extends Entitate>{
    public String ToString(T element);
    public T FromString(String Line);
}
