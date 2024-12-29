package br.com.alura.literalura.service;

import br.com.alura.literalura.model.DadosLivros;

import java.util.List;

public class RespostaAPI {
    private int count;
    private String next;
    private String previous;
    private List<DadosLivros> results; // Lista de livros

    // Getters e Setters
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<DadosLivros> getResults() {
        return results;
    }

    public void setResults(List<DadosLivros> results) {
        this.results = results;
    }
}
