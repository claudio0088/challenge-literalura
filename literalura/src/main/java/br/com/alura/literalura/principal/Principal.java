package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.DadosLivros;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO ="https://gutendex.com/books";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private List<DadosLivros> dadosLivos = new ArrayList<>();

    public List<DadosLivros> getDadosLivos() {
        return dadosLivos;
    }

    public void setDadosLivos(List<DadosLivros> dadosLivos) {
        this.dadosLivos = dadosLivos;
    }

    public void exibeMenu(){
        var opcoes = -1;

        while(opcoes == 0){
            var menu = """
                    *********************************************************
                    1 - buscar livros
                    
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
        //Serie serie = new Serie(dados);
        dadosLivos.add(dados);
        //repositorio.save(serie);
        System.out.println(dados);
    }

    private DadosLivros getDadosLivros(){
        System.out.println("Digite o nome do livro para busca");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + "?search=" + nomeLivro.replace(" ", "%20"));
        DadosLivros dados = conversor.buscaDados(json, DadosLivros.class);
        return dados;
    }
}




//var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);