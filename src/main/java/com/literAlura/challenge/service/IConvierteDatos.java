package com.literAlura.challenge.service;

public interface IConvierteDatos {

    <T> T convertirDatos(String json, Class<T> claseMapeada);


}
