package condominio;

import condominio.Fracao;

//Classe de Garagens que herda de Fracao
public class Garagens extends Fracao {
    private int numViaturas;
    private boolean temLavagem;
    
    //Construtor da classe Garagens
    public Garagens(int numViaturas, boolean temLavagem, String identificador, double area, double percentagemArea, String localizacao) {
        super(identificador, area, percentagemArea, localizacao);
        
        if (numViaturas <= 0) {
            throw new IllegalArgumentException("O número de viaturas deve ser maior que zero.");
        }
        this.numViaturas = numViaturas;
        this.temLavagem = temLavagem;
    }

    //Retorna o número de viatura
    public int getNumViaturas() {
        return numViaturas;
    }

    //Atualiza o número de viaturas com validação
    public void setNumViaturas(int numViaturas) {
        if (numViaturas <= 0) {
            throw new IllegalArgumentException("O número de viaturas deve ser maior que zero.");
        }
        this.numViaturas = numViaturas;
    }

    //Retorna se a garagem possui área de lavagem
    public boolean isTemLavagem() {
        return temLavagem;
    }

     //Atualiza o estado de possuir ou não área de lavagem
    public void setTemLavagem(boolean temLavagem) {
        this.temLavagem = temLavagem;
    }

    //Calcula a percentagem de área da garagem em relação à área total do condomínio
    @Override
    public double calcularPercentagem(double areaTotal) {
        if (areaTotal <= 0) {
            throw new IllegalArgumentException("A área total deve ser maior que zero.");
        }
        return ((this.getArea() / areaTotal) * 100);
    }

    //Calcula a quota mensal da garagem com base nas despesas gerais e do elevador
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
