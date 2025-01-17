package projectofinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Condominio {
    private String morada;
    private int identificador;
    private double totalDespesasGerais;
    private double totalDespesasElevador;
    private LocalDate dataConstrucao;
    private int numeroFracoes;
    private List<Fracao> listaFracao;

    public Condominio(String morada, int identificador, double totalDespesasGerais, double totalDespesasElevador, LocalDate dataConstrucao, int numeroFracoes) {
        
        if (morada == null || morada.trim().isEmpty()) {
            throw new IllegalArgumentException("A morada não pode estar vazia.");
        }
        if (identificador <= 0) {
            throw new IllegalArgumentException("O identificador deve ser maior que zero.");
        }
        if (totalDespesasGerais < 0) {
            throw new IllegalArgumentException("As despesas gerais não podem ser negativas.");
        }
        if (totalDespesasElevador < 0) {
            throw new IllegalArgumentException("As despesas do elevador não podem ser negativas.");
        }
        if (dataConstrucao == null) {
            throw new IllegalArgumentException("A data de construção não pode ser nula.");
        }
        if (dataConstrucao.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de construção não pode ser futura.");
        }
        if (numeroFracoes <= 0) {
            throw new IllegalArgumentException("O número de frações deve ser maior que zero.");
        }
        
        this.morada = morada;
        this.identificador = identificador;
        this.totalDespesasGerais = totalDespesasGerais;
        this.totalDespesasElevador = totalDespesasElevador;
        this.dataConstrucao = dataConstrucao;
        this.numeroFracoes = numeroFracoes;
        this.listaFracao = new ArrayList<>();
    }    

    // gets and sets
    
    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        if (morada == null || morada.trim().isEmpty()) {
            throw new IllegalArgumentException("A morada não pode estar vazia.");
        }
        this.morada = morada;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
         if (identificador <= 0) {
            throw new IllegalArgumentException("O identificador deve ser maior que zero.");
        }
        this.identificador = identificador;
    }

    public double getTotalDespesasGerais() {
        return totalDespesasGerais;
    }

    public void setTotalDespesasGerais(double totalDespesasGerais) {
         if (totalDespesasGerais < 0) {
            throw new IllegalArgumentException("As despesas gerais não podem ser negativas.");
        }
        this.totalDespesasGerais = totalDespesasGerais;
    }

    public double getTotalDespesasElevador() {
        return totalDespesasElevador;
    }

    public void setTotalDespesasElevador(double totalDespesasElevador) {
        if (totalDespesasElevador < 0) {
            throw new IllegalArgumentException("As despesas do elevador não podem ser negativas.");
        }
        this.totalDespesasElevador = totalDespesasElevador;
    }

    public LocalDate getDataConstrucao() {
        return dataConstrucao;
    }

    public void setDataConstrucao(LocalDate dataConstrucao) {
        if (dataConstrucao == null) {
            throw new IllegalArgumentException("A data de construção não pode ser nula.");
        }
        if (dataConstrucao.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de construção não pode ser futura.");
        }
        this.dataConstrucao = dataConstrucao;
    }

    public int getNumeroFracoes() {
        return numeroFracoes;
    }

    public void setNumeroFracoes(int numeroFracoes) {
        if (numeroFracoes <= 0) {
            throw new IllegalArgumentException("O número de frações deve ser maior que zero.");
        }
        this.numeroFracoes = numeroFracoes;
    }

    public List<Fracao> getFracao() {
        return listaFracao;
    }

    public void setFracao(List<Fracao> listaFracao) {
        if (listaFracao == null) {
            throw new IllegalArgumentException("A lista de frações não pode ser nula.");
        }
        this.listaFracao = listaFracao;
    }
    
    // funcoes da class condominio

    public void adicionarFracao(Fracao fracao) // metodo repetido em apartamento com o mesmo comportamento 
    {
        if (fracao == null) {
            throw new IllegalArgumentException("A fração não pode ser nula.");
        }
        if (condominioEstaCompleto()) {
            throw new IllegalStateException("O condomínio já atingiu o número máximo de frações.");
        }
        this.listaFracao.add(fracao);
    }
    
    public void removerFracao(String identificador) // elimina com base o id da frcao
    {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("O identificador não pode estar vazio.");
        }
        listaFracao.removeIf(listaFracao -> listaFracao.getIdentificador().equals(identificador));
    }
    
    public double calcularDespesasTotais() // calculo do valor total das despesas
    {
        return totalDespesasGerais + totalDespesasElevador;
    }
    
    public double calcularDespesasFracao(Fracao fracao) // calculo da despesa por fracao
    {
        if (fracao == null) {
            throw new IllegalArgumentException("A fração não pode ser nula.");
        }
        return (fracao.getPercentagemArea() / 100) * calcularDespesasTotais(); 
    }
    
    public boolean condominioEstaCompleto() // verifica se o numero de fracoes do condominio esta completo
    {
        return listaFracao.size() >= numeroFracoes;
    }
    
    // altera os dados do condominio
    public void alterarDadosCondominio(String novaMorada, double novaDespesasGerais, double novaDespesasElevadores, int novoNumFracoes)
    {
        this.morada = novaMorada;
        this.totalDespesasGerais = novaDespesasGerais;
        this.totalDespesasElevador = novaDespesasElevadores;
        this.numeroFracoes = novoNumFracoes;
    }
    
    public void infoCondominio() // mostra as informacoes do condominio
    {
        System.out.println("##############################");
        System.out.println("Identificador: " + identificador);
        System.out.println("Morada: " + morada);
        System.out.println("Data de Construção: " + dataConstrucao);
        System.out.println("Despesas Gerais: " + totalDespesasGerais);
        System.out.println("Despesas do Elevador: " + totalDespesasElevador);
        System.out.println("Numero de Frações: " + numeroFracoes);
        System.out.println("Frações do Condominio: ");
        
        if (listaFracao.isEmpty()) //  funcao identica a do proprietario (funcao repetida) -- comeca aqui
        {
            System.out.println("O Condomio não possui Frações!");
        }
        else
        {
            System.out.println();
            for (Fracao fracao : listaFracao)
            {
                System.out.println("Identificador: " + fracao.getIdentificador());
                System.out.println("Area: " + fracao.getArea());
                System.out.println("Percentagem: " + fracao.getPercentagemArea());
                System.out.println("Localizacao: " + fracao.getLocalizacao());
                System.out.println();
            }
        } // termina aqui a funcao
        System.out.println("##############################");
    }
    
}