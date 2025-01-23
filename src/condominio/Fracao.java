package condominio;

//Classe da Fracao
public abstract class Fracao {
    private String identificador;
    private double area;
    private double percentagemArea;
    private String localizacao;

    //Construtor da classe Fracao com validações
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
    
    //Retorna a localizacao da Fracao
    public String getLocalizacao() {
        return localizacao;
    }
    
    //Atualiza a localizacao com validação
    public void setLocalizacao(String localizacao) {
        if (localizacao == null || localizacao.trim().isEmpty()) {
            throw new IllegalArgumentException("A localização não pode estar vazia.");
        }
        this.localizacao = localizacao;
    }
    
    //Retorna o identificador da Fracao
    public String getIdentificador() {
        return identificador;
    }

    //Atualiza o identificador com validação
    public void setIdentificador(String identificador) {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("O identificador não pode estar vazio.");
        }
        this.identificador = identificador;
    }

    //Retorna a Area da fracao
    public double getArea() {
        return area;
    }

    //Atualiza a Area com validação
    public void setArea(double area) {
        if (area <= 0) {
            throw new IllegalArgumentException("A área deve ser maior que zero.");
        }
        this.area = area;
    }

    //Retorna a percentagem de Area da fracao
    public double getPercentagemArea() {
        return percentagemArea;
    }

    //Atualiza a percentagem da Area com validação
    public void setPercentagemArea(double percentagemArea) {
        if (percentagemArea < 0 || percentagemArea > 100) {
            throw new IllegalArgumentException("A percentagem deve estar entre 0 e 100.");
        }
        this.percentagemArea = percentagemArea;
    }
    
    //Funcoes abstratas para Calculo de Percentagem e de Quota Mensal
    public abstract double calcularPercentagem(double areaTotal);
    public abstract double calcularQuotaMensal(double despesasGerais, double despesasElevador);
}
