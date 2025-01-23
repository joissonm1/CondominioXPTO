package condominio;

// Classe do Arrecadacao que herda de Fracao
public class Arrecadacao extends Fracao {
    private boolean temPortaBlindada;

    // Construtor da classe Arrecadacao
    public Arrecadacao(boolean temPortaBlindada, String identificador, double area, double percentagemArea, String localizacao){
        super(identificador, area, percentagemArea, localizacao);
        this.temPortaBlindada = temPortaBlindada;
    }

    //Retorna true(se tiver porta blindada) e false(se tiver nao tiver porta blindada)
    public boolean isTemPortaBlindada() {
        return temPortaBlindada;
    }

    //Atualiza se tem porta blindada
    public void setTemPortaBlindada(boolean temPortaBlindada) {
        this.temPortaBlindada = temPortaBlindada;
    }
    
    // Calcula a percentagem da área da arrecadacao em relação à área total
    @Override
    public double calcularPercentagem(double areaTotal) {
        if (areaTotal <= 0) {
            throw new IllegalArgumentException("A área total deve ser maior que zero.");
        }
        return ((this.getArea() / areaTotal) * 100);
    }

    // Calcula a quota mensal baseada nas despesas gerais e de elevador
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
