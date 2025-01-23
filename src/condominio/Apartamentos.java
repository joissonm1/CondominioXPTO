package condominio;

//Classe do apartamento que herda de Fracao
public class Apartamentos extends Fracao {
    private String tipo;
    private int numCasaDeBanho;
    private int numVaranda;
    private boolean temTerraco;

    //Construtor da classe Apartamentos com validações
    public Apartamentos(String tipo, int numCasaDeBanho, int numVaranda, boolean temTerraco, String identificador, double area, double percentagemArea, String localizacao) {
        super(identificador, area, percentagemArea, localizacao);
        
        //Validação do tipo de apartamento (T0 a T5)
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo do apartamento não pode ser vazio.");
        }
        if (!tipo.matches("T[0-5]")) {
            throw new IllegalArgumentException("O tipo do apartamento deve ser T0, T1, T2, T3, T4 ou T5.");
        }
        //Validação do número de casas de banho
        if (numCasaDeBanho < 0) {
            throw new IllegalArgumentException("O número de casas de banho não pode ser negativo.");
        }
        //Validação do número de varandas
        if (numVaranda < 0) {
            throw new IllegalArgumentException("O número de varandas não pode ser negativo.");
        }

        this.tipo = tipo;
        this.numCasaDeBanho = numCasaDeBanho;
        this.numVaranda = numVaranda;
        this.temTerraco = temTerraco;
    }

    //Retorna o tipo de apartamento
    public String getTipo() {
        return tipo;
    }

    //Atualiza o tipo do apartamento com validação
    public void setTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo do apartamento não pode ser vazio.");
        }
        this.tipo = tipo;
    }

    //Retorna o número de casas de banho
    public int getNumCasaDeBanho() {
        return numCasaDeBanho;
    }

    //Atualiza o número de casas de banho com validação
    public void setNumCasaDeBanho(int numCasaDeBanho) {
        if (numCasaDeBanho < 0) {
            throw new IllegalArgumentException("O número de casas de banho não pode ser negativo.");
        }
        this.numCasaDeBanho = numCasaDeBanho;
    }

    //Retorna o número de varandas
    public int getNumVaranda() {
        return numVaranda;
    }

    //Atualiza o número de varandas com validação
    public void setNumVaranda(int numVaranda) {
        if (numVaranda < 0) {
            throw new IllegalArgumentException("O número de varandas não pode ser negativo.");
        }
        this.numVaranda = numVaranda;
    }

    //Retorna se o apartamento tem terraço
    public boolean isTemTerraco() {
        return temTerraco;
    }

    //Atualiza o estado do terraço
    public void setTemTerraco(boolean temTerraco) {
        this.temTerraco = temTerraco;
    }
    
    //Calcula a percentagem da área do apartamento em relação à área total
    @Override
    public double calcularPercentagem(double areaTotal) {
        if (areaTotal <= 0) {
            throw new IllegalArgumentException("A área total deve ser maior que zero.");
        }
        return ((this.getArea() / areaTotal) * 100);
    }

    //Calcula a quota mensal baseada nas despesas gerais e de elevador
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
