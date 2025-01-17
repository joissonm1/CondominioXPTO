package projectofinal;

public abstract class Fracao
{
    private String identificador;
    private double area;
    private double percentagemArea;
    private String localizacao;

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

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        if (localizacao == null || localizacao.trim().isEmpty()) {
            throw new IllegalArgumentException("A localização não pode estar vazia.");
        }
        this.localizacao = localizacao;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("O identificador não pode estar vazio.");
        }
        this.identificador = identificador;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        if (area <= 0) {
            throw new IllegalArgumentException("A área deve ser maior que zero.");
        }
        this.area = area;
    }

    public double getPercentagemArea() {
        return percentagemArea;
    }

    public void setPercentagemArea(double percentagemArea) {
         if (percentagemArea < 0 || percentagemArea > 100) {
            throw new IllegalArgumentException("A percentagem deve estar entre 0 e 100.");
        }
        this.percentagemArea = percentagemArea;
    }
    
    public abstract double calcularPercentagem(double areaTotal);
    public abstract double calcularQuotaMensal(double despesasGerais, double despesasElevador);
    
}
