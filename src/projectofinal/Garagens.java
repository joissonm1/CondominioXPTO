package projectofinal;

public class Garagens extends Fracao {
    private int numViaturas;
    private boolean temLavagem;

    public Garagens(int numViaturas, boolean temLavagem, String identificador, double area, double percentagemArea, String localizacao) {
        super(identificador, area, percentagemArea, localizacao);
        
        if (numViaturas <= 0) {
            throw new IllegalArgumentException("O número de viaturas deve ser maior que zero.");
        }
        this.numViaturas = numViaturas;
        this.temLavagem = temLavagem;
    }

    public int getNumViaturas() {
        return numViaturas;
    }

    public void setNumViaturas(int numViaturas) {
        if (numViaturas <= 0) {
            throw new IllegalArgumentException("O número de viaturas deve ser maior que zero.");
        }
        this.numViaturas = numViaturas;
    }

    public boolean isTemLavagem() {
        return temLavagem;
    }

    public void setTemLavagem(boolean temLavagem) {
        this.temLavagem = temLavagem;
    }

    @Override
    public double calcularPercentagem(double areaTotal) {
        if (areaTotal <= 0) {
            throw new IllegalArgumentException("A área total deve ser maior que zero.");
        }
        return ((this.getArea() / areaTotal) * 100);
    }

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