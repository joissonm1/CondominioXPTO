package projectofinal;

public class Lojas extends Fracao {

    public Lojas(String identificador, double area, double percentagemArea, String localizacao) {
        super(identificador, area, percentagemArea, localizacao);
    }

    @Override
    public double calcularQuotaMensal(double despesasGerais, double despesasElevador) {
        if (despesasGerais < 0) {
            throw new IllegalArgumentException("As despesas gerais não podem ser negativas.");
        }
        return (despesasGerais * getPercentagemArea() / 100);
    }

    @Override
    public double calcularPercentagem(double areaTotal) {
        if (areaTotal <= 0) {
            throw new IllegalArgumentException("A área total deve ser maior que zero.");
        }
        return ((this.getArea() / areaTotal) * 100);
    }
}