package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.DadosLivros;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import br.com.alura.literalura.service.RespostaAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO ="https://gutendex.com/books?search=";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private List<DadosLivros> dadosLivos = new ArrayList<>();

    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;




    public void exibeMenu(){
        var opcoes = -1;

        while(opcoes != 0){
            var menu = """
                    *********************************************************
                    1 - buscar livros pelo título
                    2 - listar livros registrados
                    3 - listar autores registrados
                    4 - listar autores vivos em um determinado ano
                    5 - listar livros em um determinado idioma
                    
                    0 - sair
                    *********************************************************
                    """;
            System.out.println(menu);
            opcoes = leitura.nextInt();
            leitura.nextLine();

            switch (opcoes){
                case 1:
                    buscarLivro();
                    break;
                case 0:
                    System.out.println("Encerrado com sucesso");
                    break;
                default:
                    System.out.println("Opção não encontrada");
                    
            }


        }

    }

    private void buscarLivro() {
        System.out.println("Digite o nome do livro para busca");
        var nomeLivro = leitura.nextLine();
        DadosLivros dados = getDadosLivros(nomeLivro);

        salvarLivro(dados);
        System.out.println(dados);
    }

    private DadosLivros getDadosLivros(String nomeLivro){

        var json = consumo.obterDados(ENDERECO +  nomeLivro.replace(" ", "+"));
        RespostaAPI resposta = conversor.buscaDados(json, RespostaAPI.class);
        List<DadosLivros> livros = resposta.getResults();
        DadosLivros dados = livros.get(0);
        return dados;
    }
    private void salvarLivro(DadosLivros dadosLivros){
        Livro livro = new Livro();
        livro.setTitulo(dadosLivros.titulo());
        livro.setNumeroDownloads(dadosLivros.numeroDownloads());
        livro.setLinguagem(dadosLivros.linguagens().get(0));

        System.out.println(dadosLivros.autores().get(0).nomeAutor());

        Autor autor;

        var autorExistente = autorRepository.findByNome(dadosLivros.autores().get(0).nomeAutor());

        if (autorExistente.isPresent()) {
            autor = autorExistente.get();

        } else {
            autor = new Autor();
            autor.setNome(dadosLivros.autores().get(0).nomeAutor());  // Assume que o livro tem pelo menos um autor
            autor.setAnoNascimento(dadosLivros.autores().get(0).anoNascimento());
            autor.setAnoMorte(dadosLivros.autores().get(0).anoMorte());
            autorRepository.save(autor);

        }
        livro.setAutor(autor);
        livroRepository.save(livro);

    }
}




//var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);