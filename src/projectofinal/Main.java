package projectofinal;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar dados do condomínio
        String moradaCondominio = InputValidator.getInput(scanner, "Morada do Condomínio: ");
        int identificadorCondominio = InputValidator.getValidInt(scanner, "Identificador do Condomínio: ");
        double totalDespesasGerais = InputValidator.getValidDouble(scanner, "Total de Despesas Gerais: ");
        double totalDespesasElevador = InputValidator.getValidDouble(scanner, "Total de Despesas com Elevador: ");
        LocalDate dataConstrucao = InputValidator.getValidDate(scanner, "Data de Construção (AAAA-MM-DD): ");
        int maxFracoes = InputValidator.getValidInt(scanner, "Número máximo de frações: ");

        Condominio condominio = new Condominio(moradaCondominio, identificadorCondominio, totalDespesasGerais, totalDespesasElevador, dataConstrucao, maxFracoes);

        // Menu interativo
        while (true) {
            System.out.println("1. Adicionar Fração");
            System.out.println("2. Remover Fração");
            System.out.println("3. Verificar Percentagens");
            System.out.println("4. Calcular Soma das Quotas Mensais");
            System.out.println("5. Sair");
            int escolha = InputValidator.getValidInt(scanner, "Escolha: ");

            switch (escolha) {
                case 1:
                    adicionarFracaoMenu(scanner, condominio);
                    break;
                case 2:
                    removerFracaoMenu(scanner, condominio);
                    break;
                case 3:
                    System.out.println(condominio.verificarPercentagens() ? "Percentagens corretas" : "Percentagens incorretas");
                    break;
                case 4:
                    System.out.println("Soma das Quotas Mensais: " + condominio.calcularSomaQuotasMensais());
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Escolha inválida");
            }
        }
    }

    private static void adicionarFracaoMenu(Scanner scanner, Condominio condominio) {
        System.out.println("Adicionar Fração:");
        String tipo = InputValidator.getValidFracaoType(scanner, "Tipo (Apartamento, Loja, Garagem, Arrecadacao): ");
        String identificador = InputValidator.getInput(scanner, "Identificador: ");
        double area = InputValidator.getValidDouble(scanner, "Área: ");
        double percentagemArea = InputValidator.getValidPercentage(scanner, "Percentagem da Área: ");
        String localizacao = InputValidator.getInput(scanner, "Localização: ");

        Fracao fracao = null;
        switch (tipo) {
            case "apartamento":
                String tipoApt = InputValidator.getValidApartmentType(scanner, "Tipo de Apartamento (T0, T1, T2, T3, T4, T5): ");
                int numCasaDeBanho = InputValidator.getValidInt(scanner, "Número de Casas de Banho: ");
                int numVaranda = InputValidator.getValidInt(scanner, "Número de Varandas: ");
                boolean temTerraco = InputValidator.getValidBoolean(scanner, "Tem Terraço (true/false): ");
                fracao = new Apartamentos(tipoApt, numCasaDeBanho, numVaranda, temTerraco, identificador, area, percentagemArea, localizacao);
                break;
            case "loja":
                fracao = new Lojas(identificador, area, percentagemArea, localizacao);
                break;
            case "garagem":
                int numViaturas = InputValidator.getValidInt(scanner, "Número de Viaturas: ");
                boolean temLavagem = InputValidator.getValidBoolean(scanner, "Tem Lavagem (true/false): ");
                fracao = new Garagens(numViaturas, temLavagem, identificador, area, percentagemArea, localizacao);
                break;
            case "arrecadacao":
                boolean temPortaBlindada = InputValidator.getValidBoolean(scanner, "Tem Porta Blindada (true/false): ");
                fracao = new Arrecadacao(temPortaBlindada, identificador, area, percentagemArea, localizacao);
                break;
            default:
                System.out.println("Tipo de fração inválido.");
                return;
        }

        try {
            condominio.adicionarFracao(fracao);
            System.out.println("Fração adicionada com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar fração: " + e.getMessage());
        }
    }

    private static void removerFracaoMenu(Scanner scanner, Condominio condominio) {
        System.out.println("Remover Fração:");
        String identificador = InputValidator.getInput(scanner, "Identificador da Fração: ");
        try {
            condominio.removerFracao(identificador);
            System.out.println("Fração removida com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao remover fração: " + e.getMessage());
        }
    }
}