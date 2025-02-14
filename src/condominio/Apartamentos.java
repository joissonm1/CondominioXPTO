package condominio;

public class Apartamentos extends Fracao {
    private String tipo;
    private int numCasaDeBanho;
    private int numVaranda;
    private boolean temTerraco;

    // Construtor da classe Apartamentos
    public Apartamentos(String tipo, int numCasaDeBanho, int numVaranda, boolean temTerraco, String identificador, double area, double percentagemArea, String localizacao) {
        super(identificador, area, percentagemArea, localizacao);
        
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo do apartamento não pode ser vazio.");
        }
        if (numCasaDeBanho < 0) {
            throw new IllegalArgumentException("O número de casas de banho não pode ser negativo.");
        }
        if (numVaranda < 0) {
            throw new IllegalArgumentException("O número de varandas não pode ser negativo.");
        }
        if (!tipo.matches("T[0-5]")) {
            throw new IllegalArgumentException("O tipo do apartamento deve ser T0, T1, T2, T3, T4 ou T5.");
        }

        this.tipo = tipo;
        this.numCasaDeBanho = numCasaDeBanho;
        this.numVaranda = numVaranda;
        this.temTerraco = temTerraco;
    }

    // Retorna o tipo do apartamento
    public String getTipo() {
        return tipo;
    }

    // Define o tipo do apartamento
    public void setTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo do apartamento não pode ser vazio.");
        }
        this.tipo = tipo;
    }

    // Retorna o número de casas de banho
    public int getNumCasaDeBanho() {
        return numCasaDeBanho;
    }

    // Define o número de casas de banho
    public void setNumCasaDeBanho(int numCasaDeBanho) {
        if (numCasaDeBanho < 0) {
            throw new IllegalArgumentException("O número de casas de banho não pode ser negativo.");
        }
        this.numCasaDeBanho = numCasaDeBanho;
    }

    // Retorna o número de varandas
    public int getNumVaranda() {
        return numVaranda;
    }

    // Define o número de varandas
    public void setNumVaranda(int numVaranda) {
        if (numVaranda < 0) {
            throw new IllegalArgumentException("O número de varandas não pode ser negativo.");
        }
        this.numVaranda = numVaranda;
    }

    // Verifica se o apartamento tem terraço
    public boolean isTemTerraco() {
        return temTerraco;
    }

    // Define se o apartamento tem terraço
    public void setTemTerraco(boolean temTerraco) {
        this.temTerraco = temTerraco;
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
