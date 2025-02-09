package condominio;

public class Arrecadacao extends Fracao {
    private boolean temPortaBlindada;

    // Construtor da classe Arrecadacao
    public Arrecadacao(boolean temPortaBlindada, String identificador, double area, double percentagemArea, String localizacao) {
        super(identificador, area, percentagemArea, localizacao);
        this.temPortaBlindada = temPortaBlindada;
    }

    // Verifica se a arrecadação tem porta blindada
    public boolean isTemPortaBlindada() {
        return temPortaBlindada;
    }

    // Define se a arrecadação tem porta blindada
    public void setTemPortaBlindada(boolean temPortaBlindada) {
        this.temPortaBlindada = temPortaBlindada;
    }
    
    // Calcula a percentagem da área total
    @Override
    public double calcularPercentagem(double areaTotal) {
        if (areaTotal <= 0) {
            throw new IllegalArgumentException("A área total deve ser maior que zero.");
        }
        return ((this.getArea() / areaTotal) * 100);
    }

    // Calcula a quota mensal
    @Override
    public double calcularQuotaMensal(double despesasGerais, double despesasElevador) {
        if (despesasGerais < 0) {
            throw new IllegalArgumentException("As despesas gerais não podem ser negativas.");
        }
        if (despesasElevador < 0) {
            throw new IllegalArgumentException("As despesas do elevador não podem ser negativas.");
        }
        return ((despesasGerais + despesasElevador) * (getPercentagemArea() / 100));
    }
}