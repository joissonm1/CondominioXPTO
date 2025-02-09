package projectofinal;

import condominio.*;
import utils.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {
    private static List<Condominio> condominios;
    private static List<Proprietario> proprietarios;
    private static InputValidator input;

    public static void main(String[] args) {
        try {
            System.setProperty("file.encoding", "UTF-8");
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro ao configurar codificação UTF-8");
        }
        
        inicializarSistema();
        boolean continuar = true;

        while (continuar) {
            exibirMenuPrincipal();
            int opcao = InputValidator.getValidInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> menuCondominios();
                case 2 -> menuFracoes();
                case 3 -> menuProprietarios();
                case 4 -> menuRelatorios();
                case 5 -> continuar = false;
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }

        finalizarSistema();
    }

    //Metodo para iniciar o Sistema
    private static void inicializarSistema() {
        condominios = new ArrayList<>();
        proprietarios = new ArrayList<>();
        input = new InputValidator();
        GestorArquivos.carregarDados(condominios, proprietarios);
        System.out.println("Sistema inicializado com sucesso!");
    }

    //Metodo para finalizar o Sistema(salva os dados)
    private static void finalizarSistema() {
        GestorArquivos.salvarDados(condominios, proprietarios);
        System.out.println("\nDados salvos com sucesso!");
        System.out.println("Sistema finalizado. Até logo!");
    }

    //Menu Principal
    private static void exibirMenuPrincipal() {
        System.out.println("\n=== GESTÃO DE CONDOMÍNIO XPTO ===");
        System.out.println("1. Gestão de Condomínios");
        System.out.println("2. Gestão de Frações");
        System.out.println("3. Gestão de Proprietários");
        System.out.println("4. Relatórios");
        System.out.println("5. Salvar e Sair");
    }

    //Menu do Condominio
    private static void menuCondominios() {
        while (true) {
            System.out.println("\n=== GESTÃO DE CONDOMÍNIOS ===");
            System.out.println("1. Adicionar Condomínio");
            System.out.println("2. Remover Condomínio");
            System.out.println("3. Listar Condomínios");
            System.out.println("4. Modificar Despesas");
            System.out.println("5. Ver Detalhes do Condomínio");
            System.out.println("6. Voltar ao Menu Principal");

            int opcao = InputValidator.getValidInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> adicionarCondominio();
                case 2 -> removerCondominio();
                case 3 -> listarCondominios();
                case 4 -> modificarDespesas();
                case 5 -> verDetalhesCondominio();
                case 6 -> {
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    //Metodo para adicionar Condominio
    private static void adicionarCondominio() {
        System.out.println("\n=== ADICIONAR CONDOMÍNIO ===");

        try {
            String morada = InputValidator.getInput("Digite a morada do condomínio: ");
            int identificador;

            while (true) {
                identificador = InputValidator.getValidInt("Digite o identificador do condomínio: ");

                boolean identificadorExiste = false;
                for (Condominio cond : condominios) {
                    if (cond.getIdentificador() == identificador) {
                        identificadorExiste = true;
                        System.out.println("Erro: Identificador de condomínio já existe! Tente novamente.");
                        break;
                    }
                }

                if (!identificadorExiste) {
                    break;
                }
            }

            double despesasGerais = InputValidator.getValidDouble("Digite o valor das despesas gerais: ");
            double despesasElevador = InputValidator.getValidDouble("Digite o valor das despesas com elevador: ");
            LocalDate dataConstrucao = InputValidator.getValidConstrucaoData("Digite a data de construção (AAAA-MM-DD): ");
            int maxFracoes = InputValidator.getValidInt("Digite o número máximo de frações: ");

            Condominio condominio = new Condominio(morada, identificador, despesasGerais,
                                                 despesasElevador, dataConstrucao, maxFracoes);
            condominios.add(condominio);
            System.out.println("Condomínio adicionado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar condomínio: " + e.getMessage());
        }
    }

    //Metodo para Remover condominio
    private static void removerCondominio() {
        if (condominios.isEmpty()) {
            System.out.println("Não existem condomínios cadastrados!");
            return;
        }

        listarCondominios();
        int id = InputValidator.getValidInt("Digite o identificador do condomínio a remover: ");
        
        Condominio condRemover = null;
        for (Condominio c : condominios) {
            if (c.getIdentificador() == id) {
                condRemover = c;
                break;
            }
        }

        if (condRemover != null) {
            condominios.remove(condRemover);
            System.out.println("Condomínio removido com sucesso!");
        } else {
            System.out.println("Condomínio não encontrado!");
        }
    }

    //Faz a listagem dos condominios
    private static void listarCondominios() {
        if (condominios.isEmpty()) {
            System.out.println("Não existem condomínios cadastrados!");
            return;
        }

        System.out.println("\n=== LISTA DE CONDOMÍNIOS ===");
        for (Condominio c : condominios) {
            System.out.println("\nID: " + c.getIdentificador());
            System.out.println("Morada: " + c.getMorada());
            System.out.println("Número de Frações: " + c.getNumeroFracoes() + "/" + c.getMaxFracoes());
            System.out.println("------------------------");
        }
    }

    //Altera as despesas
    private static void modificarDespesas() {
        if (condominios.isEmpty()) {
            System.out.println("Não existem condomínios cadastrados!");
            return;
        }

        listarCondominios();
        int id = InputValidator.getValidInt("Digite o identificador do condomínio: ");
        
        Condominio cond = null;
        for (Condominio c : condominios) {
            if (c.getIdentificador() == id) {
                cond = c;
                break;
            }
        }

        if (cond != null) {
            System.out.println("\nDespesas atuais:");
            System.out.println("Gerais: " + cond.getTotalDespesasGerais());
            System.out.println("Elevador: " + cond.getTotalDespesasElevador());

            double novasDespesasGerais = InputValidator.getValidDouble("Digite o novo valor das despesas gerais: ");
            double novasDespesasElevador = InputValidator.getValidDouble("Digite o novo valor das despesas com elevador: ");

            try {
                cond.setTotalDespesasGerais(novasDespesasGerais);
                cond.setTotalDespesasElevador(novasDespesasElevador);
                System.out.println("Despesas atualizadas com sucesso!");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao atualizar despesas: " + e.getMessage());
            }
        } else {
            System.out.println("Condomínio não encontrado!");
        }
    }

    //Apresenta os detalhes do condominio
    private static void verDetalhesCondominio() {
        if (condominios.isEmpty()) {
            System.out.println("Não existem condomínios cadastrados!");
            return;
        }

        listarCondominios();
        int id = InputValidator.getValidInt("Digite o identificador do condomínio: ");
        
        Condominio cond = null;
        for (Condominio c : condominios) {
            if (c.getIdentificador() == id) {
                cond = c;
                break;
            }
        }

        if (cond != null) {
            System.out.println("\n=== DETALHES DO CONDOMÍNIO ===");
            System.out.println("Identificador: " + cond.getIdentificador());
            System.out.println("Morada: " + cond.getMorada());
            System.out.println("Data de Construção: " + cond.getDataConstrucao());
            System.out.println("Despesas Gerais: " + cond.getTotalDespesasGerais());
            System.out.println("Despesas Elevador: " + cond.getTotalDespesasElevador());
            System.out.println("Número de Frações: " + cond.getNumeroFracoes() + "/" + cond.getMaxFracoes());
            
            if (!cond.getListaFracao().isEmpty()) {
                System.out.println("\nFrações do Condomínio:");
                for (Fracao f : cond.getListaFracao()) {
                    System.out.println("- " + f.getIdentificador() + " (" + 
                                     f.getClass().getSimpleName() + ") - " + 
                                     f.getPercentagemArea() + "%");
                }
            } else {
                System.out.println("\nNão existem frações cadastradas neste condomínio.");
            }
        } else {
            System.out.println("Condomínio não encontrado!");
        }
    }
    
    //Menu das Fracoes do Sistema
    private static void menuFracoes() {
        while (true) {
            System.out.println("\n=== GESTÃO DE FRAÇÕES ===");
            System.out.println("1. Adicionar Fração");
            System.out.println("2. Remover Fração");
            System.out.println("3. Listar Frações");
            System.out.println("4. Ver Detalhes da Fração");
            System.out.println("5. Voltar ao Menu Principal");

            int opcao = InputValidator.getValidInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> adicionarFracao();
                case 2 -> removerFracao();
                case 3 -> listarFracoes();
                case 4 -> verDetalhesFracao();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    
    //Adiciona Fracao
   private static void adicionarFracao() {
    if (condominios.isEmpty()) {
        System.out.println("Não existem condomínios cadastrados! Crie um condomínio primeiro.");
        return;
    }

    listarCondominios();
    int idCond = InputValidator.getValidInt("Digite o identificador do condomínio para adicionar a fração: ");
    
    Condominio cond = null;
    for (Condominio c : condominios) {
        if (c.getIdentificador() == idCond) {
            cond = c;
            break;
        }
    }

    if (cond == null) {
        System.out.println("Condomínio não encontrado!");
        return;
    }

    // Verificação do limite de frações ANTES de coletar dados
    if (cond.getNumeroFracoes() >= cond.getMaxFracoes()) {
        System.out.println("\nErro: Este condomínio já atingiu o limite máximo de " + 
                         cond.getMaxFracoes() + " frações!");
        return;
    }

    System.out.println("\nTipos de Fração:");
    System.out.println("1. Apartamento");
    System.out.println("2. Loja");
    System.out.println("3. Garagem");
    System.out.println("4. Arrecadação");

    int tipoFracao = InputValidator.getValidInt("Escolha o tipo de fração: ");
    
    String identificador = InputValidator.getInput("Digite o identificador da fração: ");
    
    for (Fracao fracao : cond.getListaFracao()) {
        if (fracao.getIdentificador().equals(String.valueOf(identificador))) {
            System.out.println("Erro: Identificador de fração já existe!");
            return;
        }
    }
    double area = InputValidator.getValidDouble("Digite a área da fração: ");
    String localizacao = InputValidator.getInput("Digite a localização da fração: ");

    try {
        Fracao novaFracao = null;

        switch (tipoFracao) {
            case 1 -> {
                String tipoApt = InputValidator.getValidApartmentType("Digite o tipo do apartamento (T0-T5): ");
                int numCasaBanho = InputValidator.getValidInt("Digite o número de casas de banho: ");
                int numVaranda = InputValidator.getValidInt("Digite o número de varandas: ");
                boolean temTerraco = InputValidator.getValidBoolean("Tem terraço? (true/false): ");
                
                novaFracao = new Apartamentos(tipoApt, numCasaBanho, numVaranda, temTerraco,
                        identificador, area, 0.0, localizacao);
            }

            case 2 -> // Loja
                novaFracao = new Lojas(identificador, area, 0.0, localizacao);

            case 3 -> {
                int numViaturas = InputValidator.getValidInt("Digite o número de viaturas: ");
                boolean temLavagem = InputValidator.getValidBoolean("Tem serviço de lavagem? (true/false): ");
                
                novaFracao = new Garagens(numViaturas, temLavagem, identificador,
                        area, 0.0, localizacao);
            }

            case 4 -> {
                boolean temPortaBlindada = InputValidator.getValidBoolean("Tem porta blindada? (true/false): ");
                
                novaFracao = new Arrecadacao(temPortaBlindada, identificador,
                        area, 0.0, localizacao);
            }

            default -> {
                System.out.println("Tipo de fração inválido!");
                return;
            }
        }

        // Verificação redundante para segurança
        if (cond.getNumeroFracoes() < cond.getMaxFracoes()) {
            cond.adicionarFracao(novaFracao);
            System.out.println("Fração adicionada com sucesso!");
        } else {
            System.out.println("Erro: Limite de frações foi atingido durante o processo!");
        }
        
    } catch (IllegalArgumentException e) {
        System.out.println("Erro ao adicionar fração: " + e.getMessage());
    }
}

   //Responsavel por remover uma Fracao
    private static void removerFracao() {
        if (condominios.isEmpty()) {
            System.out.println("Não existem condomínios cadastrados!");
            return;
        }

        listarCondominios();
        int idCond = InputValidator.getValidInt("Digite o identificador do condomínio: ");
        
        Condominio cond = null;
        for (Condominio c : condominios) {
            if (c.getIdentificador() == idCond) {
                cond = c;
                break;
            }
        }

        if (cond == null) {
            System.out.println("Condomínio não encontrado!");
            return;
        }

        if (cond.getListaFracao().isEmpty()) {
            System.out.println("Não existem frações neste condomínio!");
            return;
        }

        System.out.println("\nFrações do Condomínio:");
        for (Fracao f : cond.getListaFracao()) {
            System.out.println("ID: " + f.getIdentificador() + " - Tipo: " + 
                             f.getClass().getSimpleName() + " - Área: " + f.getArea());
        }

        String idFracao = InputValidator.getInput("Digite o identificador da fração a remover: ");
        cond.removerFracao(idFracao);
        System.out.println("Fração removida com sucesso!");
    }

    //Lista as Fracoes
    private static void listarFracoes() {
        if (condominios.isEmpty()) {
            System.out.println("Não existem condomínios cadastrados!");
            return;
        }

        System.out.println("\n=== LISTA DE TODAS AS FRAÇÕES ===");
        for (Condominio c : condominios) {
            System.out.println("\nCondomínio: " + c.getIdentificador() + " - " + c.getMorada());
            if (c.getListaFracao().isEmpty()) {
                System.out.println("Não existem frações neste condomínio.");
            } else {
                for (Fracao f : c.getListaFracao()) {
                    System.out.println("- ID: " + f.getIdentificador() + 
                                     " | Tipo: " + f.getClass().getSimpleName() +
                                     " | Área: " + f.getArea() + 
                                     " | Percentagem: " + f.getPercentagemArea() + "%");
                }
            }
            System.out.println("------------------------");
        }
    }

    //Apresenta de maneira detalhada cada Fracao
    private static void verDetalhesFracao() {
        if (condominios.isEmpty()) {
            System.out.println("Não existem condomínios cadastrados!");
            return;
        }

        listarCondominios();
        int idCond = InputValidator.getValidInt("Digite o identificador do condomínio: ");
        
        Condominio cond = null;
        for (Condominio c : condominios) {
            if (c.getIdentificador() == idCond) {
                cond = c;
                break;
            }
        }

        if (cond == null) {
            System.out.println("Condomínio não encontrado!");
            return;
        }

        if (cond.getListaFracao().isEmpty()) {
            System.out.println("Não existem frações neste condomínio!");
            return;
        }

        System.out.println("\nFrações do Condomínio:");
        for (Fracao f : cond.getListaFracao()) {
            System.out.println("ID: " + f.getIdentificador() + " - Tipo: " + 
                             f.getClass().getSimpleName());
        }

        String idFracao = InputValidator.getInput("Digite o identificador da fração: ");
        
        for (Fracao f : cond.getListaFracao()) {
            if (f.getIdentificador().equals(idFracao)) {
                System.out.println("\n=== DETALHES DA FRAÇÃO ===");
                System.out.println("Identificador: " + f.getIdentificador());
                System.out.println("Tipo: " + f.getClass().getSimpleName());
                System.out.println("Área: " + f.getArea());
                System.out.println("Percentagem: " + f.getPercentagemArea() + "%");
                System.out.println("Localização: " + f.getLocalizacao());
                System.out.println("Quota Mensal: " + 
                    f.calcularQuotaMensal(cond.getTotalDespesasGerais(), cond.getTotalDespesasElevador()));

               
                // Detalhes específicos por tipo
                if (f instanceof Apartamentos) {
                    Apartamentos apt = (Apartamentos) f;
                    System.out.println("Tipo de Apartamento: " + apt.getTipo());
                    System.out.println("Número de Casas de Banho: " + apt.getNumCasaDeBanho());
                    System.out.println("Número de Varandas: " + apt.getNumVaranda());
                    System.out.println("Tem Terraço: " + apt.isTemTerraco());
                } else if (f instanceof Garagens) {
                    Garagens gar = (Garagens) f;
                    System.out.println("Número de Viaturas: " + gar.getNumViaturas());
                    System.out.println("Tem Lavagem: " + gar.isTemLavagem());
                } else if (f instanceof Arrecadacao) {
                    Arrecadacao arr = (Arrecadacao) f;
                    System.out.println("Tem Porta Blindada: " + arr.isTemPortaBlindada());
                }
                return;
            }
        }
        System.out.println("Fração não encontrada!");
    }
    
    //Apresenta o menu do Proprietario
    private static void menuProprietarios() {
        while (true) {
            System.out.println("\n=== GESTÃO DE PROPRIETÁRIOS ===");
            System.out.println("1. Adicionar Proprietário");
            System.out.println("2. Remover Proprietário");
            System.out.println("3. Listar Proprietários");
            System.out.println("4. Associar Fração a Proprietário");
            System.out.println("5. Ver Detalhes do Proprietário");
            System.out.println("6. Voltar ao Menu Principal");

            int opcao = InputValidator.getValidInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> adicionarProprietario();
                case 2 -> removerProprietario();
                case 3 -> listarProprietarios();
                case 4 -> associarFracaoProprietario();
                case 5 -> verDetalhesProprietario();
                case 6 -> {
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    //Metodo para adicionar Proprietarios
    private static void adicionarProprietario() {
        System.out.println("\n=== ADICIONAR PROPRIETÁRIO ===");
        
        try {
            String identificador = InputValidator.getInput("Digite o identificador do proprietário: ");
            String nome = InputValidator.getInput("Digite o nome do proprietário: ");
            String morada = InputValidator.getInput("Digite a morada do proprietário: ");
            String telefone = InputValidator.getValidPhoneNumber("Digite o telefone do proprietário: ");
            String telemovel = InputValidator.getValidPhoneNumber("Digite o telemóvel do proprietário: ");
            String email = InputValidator.getValidEmail("Digite o email do proprietário: ");
            LocalDate dataNascimento = InputValidator.getValidNascimentoData("Digite a data de nascimento (AAAA-MM-DD): ");

            Proprietario proprietario = new Proprietario(identificador, nome, morada, telefone,
                                                       telemovel, email, dataNascimento);
            proprietarios.add(proprietario);
            System.out.println("Proprietário adicionado com sucesso!");
            
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar proprietário: " + e.getMessage());
        }
    }

    //Remove Proprietario do Sistema
    private static void removerProprietario() {
        if (proprietarios.isEmpty()) {
            System.out.println("Não existem proprietários cadastrados!");
            return;
        }

        listarProprietarios();
        String id = InputValidator.getInput("Digite o identificador do proprietário a remover: ");
        
        Proprietario propRemover = null;
        for (Proprietario p : proprietarios) {
            if (p.getIdentificador().equals(id)) {
                propRemover = p;
                break;
            }
        }

        if (propRemover != null) {
            proprietarios.remove(propRemover);
            System.out.println("Proprietário removido com sucesso!");
        } else {
            System.out.println("Proprietário não encontrado!");
        }
    }

    //Faz a listagem dos proprietarios
    private static void listarProprietarios() {
        if (proprietarios.isEmpty()) {
            System.out.println("Não existem proprietários cadastrados!");
            return;
        }

        System.out.println("\n=== LISTA DE PROPRIETÁRIOS ===");
        for (Proprietario p : proprietarios) {
            System.out.println("\nID: " + p.getIdentificador());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Email: " + p.getEmail());
            System.out.println("Número de Frações: " + p.getListaFracao().size());
            System.out.println("------------------------");
        }
    }

    //Adiciona Fracao ao Proprietario escolhido
    private static void associarFracaoProprietario() {
        if (proprietarios.isEmpty()) {
            System.out.println("Não existem proprietários cadastrados!");
            return;
        }

        if (condominios.isEmpty()) {
            System.out.println("Não existem condomínios cadastrados!");
            return;
        }

        listarProprietarios();
        String idProp = InputValidator.getInput("Digite o identificador do proprietário: ");
        
        Proprietario prop = null;
        for (Proprietario p : proprietarios) {
            if (p.getIdentificador().equals(idProp)) {
                prop = p;
                break;
            }
        }

        if (prop == null) {
            System.out.println("Proprietário não encontrado!");
            return;
        }

        listarFracoesDisponiveis();
        String idFracao = InputValidator.getInput("Digite o identificador da fração a associar: ");

        // Buscar a fração em todos os condomínios
        for (Condominio c : condominios) {
            for (Fracao f : c.getListaFracao()) {
                if (f.getIdentificador().equals(idFracao)) {
                    try {
                        prop.adicionarFracao(f);
                        System.out.println("Fração associada com sucesso!");
                        return;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro ao associar fração: " + e.getMessage());
                        return;
                    }
                }
            }
        }
        System.out.println("Fração não encontrada!");
    }

    //Mostra detalhadamente cada Proprietario
    private static void verDetalhesProprietario() {
        if (proprietarios.isEmpty()) {
            System.out.println("Não existem proprietários cadastrados!");
            return;
        }

        listarProprietarios();
        String id = InputValidator.getInput("Digite o identificador do proprietário: ");
        
        for (Proprietario p : proprietarios) {
            if (p.getIdentificador().equals(id)) {
                System.out.println("\n=== DETALHES DO PROPRIETÁRIO ===");
                System.out.println("Identificador: " + p.getIdentificador());
                System.out.println("Nome: " + p.getNome());
                System.out.println("Morada: " + p.getMorada());
                System.out.println("Telefone: " + p.getTelefone());
                System.out.println("Telemóvel: " + p.getTelemovel());
                System.out.println("Email: " + p.getEmail());
                System.out.println("Data de Nascimento: " + p.getDataNascimento());
                
                List<Fracao> fracoes = p.getListaFracao();
                if (!fracoes.isEmpty()) {
                    System.out.println("\nFrações Associadas:");
                    for (Fracao f : fracoes) {
                        System.out.println("- " + f.getIdentificador() + " (" + 
                                         f.getClass().getSimpleName() + ") - " + 
                                         "Área: " + f.getArea() + "m²");
                    }
                } else {
                    System.out.println("\nNão possui frações associadas.");
                }
                return;
            }
        }
        System.out.println("Proprietário não encontrado!");
    }

    //Apresenta todas as Fracoes disponiveis
    private static void listarFracoesDisponiveis() {
        System.out.println("\n=== FRAÇÕES DISPONÍVEIS ===");
        for (Condominio c : condominios) {
            System.out.println("\nCondomínio: " + c.getIdentificador() + " - " + c.getMorada());
            if (c.getListaFracao().isEmpty()) {
                System.out.println("Não existem frações neste condomínio.");
            } else {
                for (Fracao f : c.getListaFracao()) {
                    System.out.println("- ID: " + f.getIdentificador() + 
                                     " | Tipo: " + f.getClass().getSimpleName() +
                                     " | Área: " + f.getArea() + "m²");
                }
            }
            System.out.println("------------------------");
        }
    }
    
    //Apresenta o menu dos relatorios
    private static void menuRelatorios() {
        while (true) {
            System.out.println("\n=== RELATÓRIOS ===");
            System.out.println("1. Verificar Percentagens");
            System.out.println("2. Calcular Quotas Mensais");
            System.out.println("3. Listar Frações por Tipo");
            System.out.println("4. Voltar ao Menu Principal");

            int opcao = InputValidator.getValidInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> verificarPercentagens();
                case 2 -> calcularQuotasMensais();
                case 3 -> listarFracoesPorTipo();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    //Verifica a percentagem
    private static void verificarPercentagens() {
        if (condominios.isEmpty()) {
            System.out.println("Não existem condomínios cadastrados!");
            return;
        }

        listarCondominios();
        int idCond = InputValidator.getValidInt("Digite o identificador do condomínio: ");
        
        Condominio cond = null;
        for (Condominio c : condominios) {
            if (c.getIdentificador() == idCond) {
                cond = c;
                break;
            }
        }

        if (cond == null) {
            System.out.println("Condomínio não encontrado!");
            return;
        }

        if (cond.getListaFracao().isEmpty()) {
            System.out.println("Não existem frações neste condomínio!");
            return;
        }

        System.out.println("\n=== VERIFICAÇÃO DE PERCENTAGENS ===");
        System.out.println("Condomínio: " + cond.getMorada());
        
        double somaPercentagens = 0.0;
        for (Fracao f : cond.getListaFracao()) {
            System.out.printf("Fração %s: %.3f%%\n", f.getIdentificador(), f.getPercentagemArea());
            somaPercentagens += f.getPercentagemArea();
        }

        System.out.printf("\nSoma total das percentagens: %.3f%%\n", somaPercentagens);
        if (Math.abs(somaPercentagens - 100.0) < 0.001) {
            System.out.println("Status: CORRETO - A soma das percentagens é 100%");
        } else {
            System.out.println("Status: INCORRETO - A soma das percentagens não é 100%");
        }
    }

    //Faz o Calculo das quotas Mensais dos condominios
    private static void calcularQuotasMensais() {
        if (condominios.isEmpty()) {
            System.out.println("Não existem condomínios cadastrados!");
            return;
        }

        listarCondominios();
        int idCond = InputValidator.getValidInt("Digite o identificador do condomínio: ");
        
        Condominio cond = null;
        for (Condominio c : condominios) {
            if (c.getIdentificador() == idCond) {
                cond = c;
                break;
            }
        }

        if (cond == null) {
            System.out.println("Condomínio não encontrado!");
            return;
        }

        if (cond.getListaFracao().isEmpty()) {
            System.out.println("Não existem frações neste condomínio!");
            return;
        }

        System.out.println("\n=== QUOTAS MENSAIS ===");
        System.out.println("Condomínio: " + cond.getMorada());
        System.out.println("Despesas Gerais: " + cond.getTotalDespesasGerais());
        System.out.println("Despesas Elevador: " + cond.getTotalDespesasElevador());
        System.out.println("\nQuotas por Fração:");

        double totalQuotas = 0.0;
        for (Fracao f : cond.getListaFracao()) {
            double quota = f.calcularQuotaMensal(cond.getTotalDespesasGerais(), 
                                               cond.getTotalDespesasElevador());
            System.out.printf("Fração %s (%s): %.2f€\n", 
                            f.getIdentificador(), 
                            f.getClass().getSimpleName(), 
                            quota);
            totalQuotas += quota;
        }

        System.out.printf("\nTotal das Quotas Mensais: %.2f€\n", totalQuotas);
        System.out.printf("Total das Despesas: %.2f€\n", 
                         cond.getTotalDespesasGerais() + cond.getTotalDespesasElevador());
    }

    //Lista as Fracoes por tipo
    private static void listarFracoesPorTipo() {
        if (condominios.isEmpty()) {
            System.out.println("Não existem condomínios cadastrados!");
            return;
        }

        System.out.println("\n=== FRAÇÕES POR TIPO ===");
        
        // Contadores para cada tipo
        int totalApartamentos = 0;
        int totalLojas = 0;
        int totalGaragens = 0;
        int totalArrecadacoes = 0;

        // Para cada tipo, vamos listar e contar
        System.out.println("\nAPARTAMENTOS:");
        for (Condominio c : condominios) {
            for (Fracao f : c.getListaFracao()) {
                if (f instanceof Apartamentos apt) {
                    System.out.println("- Condomínio " + c.getIdentificador() + 
                                     ": " + apt.getIdentificador() + 
                                     " (Tipo " + apt.getTipo() + ")");
                    totalApartamentos++;
                }
            }
        }

        System.out.println("\nLOJAS:");
        for (Condominio c : condominios) {
            for (Fracao f : c.getListaFracao()) {
                if (f instanceof Lojas) {
                    System.out.println("- Condomínio " + c.getIdentificador() + 
                                     ": " + f.getIdentificador());
                    totalLojas++;
                }
            }
        }

        System.out.println("\nGARAGENS:");
        for (Condominio c : condominios) {
            for (Fracao f : c.getListaFracao()) {
                if (f instanceof Garagens gar) {
                    System.out.println("- Condomínio " + c.getIdentificador() + 
                                     ": " + gar.getIdentificador() + 
                                     " (" + gar.getNumViaturas() + " viaturas)");
                    totalGaragens++;
                }
            }
        }

        System.out.println("\nARRECADAÇÕES:");
        for (Condominio c : condominios) {
            for (Fracao f : c.getListaFracao()) {
                if (f instanceof Arrecadacao) {
                    System.out.println("- Condomínio " + c.getIdentificador() + 
                                     ": " + f.getIdentificador());
                    totalArrecadacoes++;
                }
            }
        }

        // Resumo final
        System.out.println("\n=== RESUMO ===");
        System.out.println("Total de Apartamentos: " + totalApartamentos);
        System.out.println("Total de Lojas: " + totalLojas);
        System.out.println("Total de Garagens: " + totalGaragens);
        System.out.println("Total de Arrecadações: " + totalArrecadacoes);
        System.out.println("Total de Frações: " + 
                          (totalApartamentos + totalLojas + totalGaragens + totalArrecadacoes));
    }
}