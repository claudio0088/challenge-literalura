package br.com.alura.literalura.service;

public interface IConverteDados {
    <T> T buscaDados(String json, Class<T>classe);
}
