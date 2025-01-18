package projectofinal;

import utils.InputValidator;
import condominio.Lojas;
import condominio.Garagens;
import condominio.Fracao;
import condominio.Condominio;
import condominio.Arrecadacao;
import condominio.Apartamentos;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        // Solicitar dados do condomínio
        String moradaCondominio = InputValidator.getInput("Morada do Condomínio: ");
        int identificadorCondominio = InputValidator.getValidInt( "Identificador do Condomínio: ");
        double totalDespesasGerais = InputValidator.getValidDouble("Total de Despesas Gerais: ");
        double totalDespesasElevador = InputValidator.getValidDouble("Total de Despesas com Elevador: ");
        LocalDate dataConstrucao = InputValidator.getValidDate("Data de Construção (AAAA-MM-DD): ");
        int maxFracoes = InputValidator.getValidInt("Número máximo de frações: ");

        Condominio condominio = new Condominio(moradaCondominio, identificadorCondominio, totalDespesasGerais, totalDespesasElevador, dataConstrucao, maxFracoes);

        // Menu interativo
        while (true) {
            System.out.println("1. Adicionar Fração");
            System.out.println("2. Remover Fração");
            System.out.println("3. Verificar Percentagens");
            System.out.println("4. Calcular Soma das Quotas Mensais");
            System.out.println("5. Sair");
            int escolha = InputValidator.getValidInt("Escolha: ");

            switch (escolha) {
                case 1 -> adicionarFracaoMenu(condominio);
                case 2 -> removerFracaoMenu(condominio);
                case 3 -> System.out.println(condominio.verificarPercentagens() ? "Percentagens corretas" : "Percentagens incorretas");
                case 4 -> System.out.println("Soma das Quotas Mensais: " + condominio.calcularSomaQuotasMensais());
                case 5 -> {
                    return;
                }
                default -> System.out.println("Escolha inválida");
            }
        }
    }

    private static void adicionarFracaoMenu(Condominio condominio) {
        if (condominio.getListaFracao().size() >= condominio.getMaxFracoes()) {
            System.out.println("Erro: O número máximo de frações já foi atingido.");
            return;
        }

        System.out.println("Adicionar Fração:");
        String tipo = InputValidator.getValidFracaoType("Tipo (Apartamento, Loja, Garagem, Arrecadacao): ");
        String identificador = InputValidator.getInput("Identificador: ");
        double area = InputValidator.getValidDouble("Área: ");
        double percentagemArea = InputValidator.getValidPercentage("Percentagem da Área: ");
        String localizacao = InputValidator.getInput("Localização: ");

        Fracao fracao = null;
        switch (tipo) {
            case "apartamento" -> {
                String tipoApt = InputValidator.getValidApartmentType("Tipo de Apartamento (T0, T1, T2, T3, T4, T5): ");
                int numCasaDeBanho = InputValidator.getValidInt("Número de Casas de Banho: ");
                int numVaranda = InputValidator.getValidInt("Número de Varandas: ");
                boolean temTerraco = InputValidator.getValidBoolean("Tem Terraço (true/false): ");
                fracao = new Apartamentos(tipoApt, numCasaDeBanho, numVaranda, temTerraco, identificador, area, percentagemArea, localizacao);
            }
            case "loja" -> fracao = new Lojas(identificador, area, percentagemArea, localizacao);
            case "garagem" -> {
                int numViaturas = InputValidator.getValidInt("Número de Viaturas: ");
                boolean temLavagem = InputValidator.getValidBoolean("Tem Lavagem (true/false): ");
                fracao = new Garagens(numViaturas, temLavagem, identificador, area, percentagemArea, localizacao);
            }
            case "arrecadacao" -> {
                boolean temPortaBlindada = InputValidator.getValidBoolean("Tem Porta Blindada (true/false): ");
                fracao = new Arrecadacao(temPortaBlindada, identificador, area, percentagemArea, localizacao);
            }
            default -> {
                System.out.println("Tipo de fração inválido.");
                return;
            }
        }

        try {
            condominio.adicionarFracao(fracao);
            System.out.println("Fração adicionada com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar fração: " + e.getMessage());
        }
    }

    private static void removerFracaoMenu(Condominio condominio) {
        System.out.println("Remover Fração:");
        String identificador = InputValidator.getInput("Identificador da Fração: ");
        try {
            condominio.removerFracao(identificador);
            System.out.println("Fração removida com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao remover fração: " + e.getMessage());
        }
    }
}