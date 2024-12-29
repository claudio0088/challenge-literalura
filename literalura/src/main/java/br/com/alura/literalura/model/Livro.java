package br.com.alura.literalura.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private String titulo;
     private String linguagem;
     private Double numeroDownloads;

    // Relacionamento Muitos-para-Um (muitos livros para um autor)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
     private Autor autor;

     public Livro() {}


     public Livro(DadosLivros dadosLivros){
         this.titulo = dadosLivros.titulo();
         this.linguagem = dadosLivros.linguagem();
         this.numeroDownloads = dadosLivros.numeroDownloads();
     }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }

    public Double getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Double numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "******** LIVRO ********" +
                "Título: " + titulo +
                "Autor: " + autor +
                "Linguagem: " + linguagem + '\'' +
                "Numero de downloads:" + numeroDownloads+
                "**********************";
    }
}
