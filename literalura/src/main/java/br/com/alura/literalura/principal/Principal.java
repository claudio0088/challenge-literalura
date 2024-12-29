package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.DadosLivros;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import br.com.alura.literalura.service.RespostaAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO ="https://gutendex.com/books?search=";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private List<DadosLivros> dadosLivos = new ArrayList<>();

    private LivroRepository repositorio;

    public Principal(LivroRepository repositorio) {
        this.repositorio = repositorio;
    }


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
        DadosLivros dados = getDadosLivros();
        Livro livro = new Livro(dados);
        dadosLivos.add(dados);
        //repositorio.save(livro);
        System.out.println(dados);
    }

    private DadosLivros getDadosLivros(){
        System.out.println("Digite o nome do livro para busca");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO +  nomeLivro.replace(" ", "+"));

        RespostaAPI resposta = conversor.buscaDados(json, RespostaAPI.class);
        List<DadosLivros> livros = resposta.getResults();
        DadosLivros dados = livros.get(0);
        return dados;
    }
}




//var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);