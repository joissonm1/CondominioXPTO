package condominio;

public abstract class Fracao {
    private String identificador;
    private double area;
    private double percentagemArea;
    private String localizacao;

    // Construtor da classe Fracao
    public Fracao(String identificador, double area, double percentagemArea, String localizacao) {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("O identificador não pode estar vazio.");
        }
        if (area <= 0) {
            throw new IllegalArgumentException("A área deve ser maior que zero.");
        }
        if (percentagemArea < 0 || percentagemArea > 100) {
            throw new IllegalArgumentException("A percentagem deve estar entre 0 e 100.");
        }
        if (localizacao == null || localizacao.trim().isEmpty()) {
            throw new IllegalArgumentException("A localização não pode estar vazia.");
        }
        
        this.identificador = identificador;
        this.area = area;
        this.percentagemArea = percentagemArea;
        this.localizacao = localizacao;
    }

    // Retorna a localização da fração
    public String getLocalizacao() {
        return localizacao;
    }

    // Define a localização da fração
    public void setLocalizacao(String localizacao) {
        if (localizacao == null || localizacao.trim().isEmpty()) {
            throw new IllegalArgumentException("A localização não pode estar vazia.");
        }
        this.localizacao = localizacao;
    }

    // Retorna o identificador da fração
    public String getIdentificador() {
        return identificador;
    }

    // Define o identificador da fração
    public void setIdentificador(String identificador) {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("O identificador não pode estar vazio.");
        }
        this.identificador = identificador;
    }

    // Retorna a área da fração
    public double getArea() {
        return area;
    }

    // Define a área da fração
    public void setArea(double area) {
        if (area <= 0) {
            throw new IllegalArgumentException("A área deve ser maior que zero.");
        }
        this.area = area;
    }

    // Retorna a percentagem da área da fração
    public double getPercentagemArea() {
        return percentagemArea;
    }

    // Define a percentagem da área da fração
    public void setPercentagemArea(double percentagemArea) {
        if (percentagemArea < 0 || percentagemArea > 100) {
            throw new IllegalArgumentException("A percentagem deve estar entre 0 e 100.");
        }
        this.percentagemArea = percentagemArea;
    }
    
    // Método abstrato para calcular a percentagem da área total
    public abstract double calcularPercentagem(double areaTotal);

    // Método abstrato para calcular a quota mensal
    public abstract double calcularQuotaMensal(double despesasGerais, double despesasElevador);
}