package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(@JsonAlias("title") String titulo,
                          @JsonAlias("authors") List<DadosAutor> autores,
                          @JsonAlias("languages") List<String> linguagens,
                          @JsonAlias("download_count") Double numeroDownloads) {


@Override
public String toString() {
    String nomesAutores = autores.stream()
            .map(DadosAutor::nomeAutor)
            .collect(Collectors.joining(", "));
    return  "--------------------- LIVRO ---------------------\n" +
            "Título              : " + titulo + "\n" +
            "Autor               : " +  nomesAutores +"\n" +
            "idioma              : " +String.join(", ", linguagens) +"\n" +
            "Número de downloads : " + numeroDownloads +"\n" +
            "--------------------------------------------------";

}

public List<String> getIdiomasComNome() {
    Map<String, String> idiomaMap = Map.of(
            "pt", "Portuguese",
            "en", "English",
            "es", "Spanish"
            // Adicione outros idiomas conforme necessário
    );

    return linguagens.stream()
            .map(idiomaMap::get)
            .toList(); // Retorna a lista de nomes dos idiomas
    }
}
