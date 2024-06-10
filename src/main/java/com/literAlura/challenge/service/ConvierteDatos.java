package com.literAlura.challenge.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {

    private ObjectMapper mapeador = new ObjectMapper();

    @Override
    public <T> T convertirDatos(String json, Class<T> claseMapeada){
        try {
            return mapeador.readValue(json, claseMapeada);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }


}

