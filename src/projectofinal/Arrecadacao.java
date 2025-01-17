package projectofinal;

public class Arrecadacao extends Fracao
{
    private boolean temPortaBlindada;

    public Arrecadacao(boolean temPortaBlindada, String identificador, double area, double percentagemArea, String localizacao) {
        super(identificador, area, percentagemArea, localizacao);
        this.temPortaBlindada = temPortaBlindada;
    }

    public boolean isTemPortaBlindada() {
        return temPortaBlindada;
    }

    public void setTemPortaBlindada(boolean temPortaBlindada) {
        this.temPortaBlindada = temPortaBlindada;
    }
    
     @Override
    public double calcularPercentagem(double areaTotal)
    {
       if (areaTotal <= 0)
       {
            throw new IllegalArgumentException("A área total deve ser maior que zero.");
       }
       return ((this.getArea() / areaTotal) * 100);
    }

    @Override
    public double calcularQuotaMensal(double despesasGerais, double despesasElevador)
    {
        if (despesasGerais < 0) {
            throw new IllegalArgumentException("As despesas gerais não podem ser negativas.");
        }
        if (despesasElevador < 0) {
            throw new IllegalArgumentException("As despesas do elevador não podem ser negativas.");
        }
        return((despesasGerais + despesasElevador) * (getPercentagemArea() / 100));
    }
}
