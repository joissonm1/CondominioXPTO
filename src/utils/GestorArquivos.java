package utils;

import condominio.*;
import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class GestorArquivos {
    private static final String CONDOMINIOS_FILE = "data/condominios.txt";
    private static final String PROPRIETARIOS_FILE = "data/proprietarios.txt";

    // Método para salvar todos os dados
    public static void salvarDados(List<Condominio> condominios, List<Proprietario> proprietarios) {
        salvarCondominios(condominios);
        salvarProprietarios(proprietarios);
    }

    // Método para carregar todos os dados
    public static void carregarDados(List<Condominio> condominios, List<Proprietario> proprietarios) {
        carregarCondominios(condominios);
        carregarProprietarios(proprietarios);
    }

    // Método para salvar a lista de condomínios em um arquivo
    private static void salvarCondominios(List<Condominio> condominios) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CONDOMINIOS_FILE))) {
            for (Condominio cond : condominios) {
                writer.println("CONDOMINIO");
                writer.println(cond.getIdentificador());
                writer.println(cond.getMorada());
                writer.println(cond.getTotalDespesasGerais());
                writer.println(cond.getTotalDespesasElevador());
                writer.println(cond.getDataConstrucao());
                writer.println(cond.getMaxFracoes());
                
                // Salvar frações do condomínio
                List<Fracao> fracoes = cond.getListaFracao();
                writer.println("FRACOES:" + fracoes.size());
                for (Fracao fracao : fracoes) {
                    salvarFracao(writer, fracao);
                }
                writer.println("FIM_CONDOMINIO");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar condomínios: " + e.getMessage());
        }
    }

    // Método para salvar uma fração em um arquivo
    private static void salvarFracao(PrintWriter writer, Fracao fracao) {
        if (fracao instanceof Apartamentos apt) {
            writer.println("APARTAMENTO");
            writer.println(apt.getIdentificador());
            writer.println(apt.getArea());
            writer.println(apt.getPercentagemArea());
            writer.println(apt.getLocalizacao());
            writer.println(apt.getTipo());
            writer.println(apt.getNumCasaDeBanho());
            writer.println(apt.getNumVaranda());
            writer.println(apt.isTemTerraco());
        } else if (fracao instanceof Lojas loja) {
            writer.println("LOJA");
            writer.println(loja.getIdentificador());
            writer.println(loja.getArea());
            writer.println(loja.getPercentagemArea());
            writer.println(loja.getLocalizacao());
        } else if (fracao instanceof Garagens garagem) {
            writer.println("GARAGEM");
            writer.println(garagem.getIdentificador());
            writer.println(garagem.getArea());
            writer.println(garagem.getPercentagemArea());
            writer.println(garagem.getLocalizacao());
            writer.println(garagem.getNumViaturas());
            writer.println(garagem.isTemLavagem());
        } else if (fracao instanceof Arrecadacao arr) {
            writer.println("ARRECADACAO");
            writer.println(arr.getIdentificador());
            writer.println(arr.getArea());
            writer.println(arr.getPercentagemArea());
            writer.println(arr.getLocalizacao());
            writer.println(arr.isTemPortaBlindada());
        }
    }

    // Método para salvar a lista de proprietários em um arquivo
    private static void salvarProprietarios(List<Proprietario> proprietarios) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PROPRIETARIOS_FILE))) {
            for (Proprietario prop : proprietarios) {
                writer.println("PROPRIETARIO");
                writer.println(prop.getIdentificador());
                writer.println(prop.getNome());
                writer.println(prop.getMorada());
                writer.println(prop.getTelefone());
                writer.println(prop.getTelemovel());
                writer.println(prop.getEmail());
                writer.println(prop.getDataNascimento());
                
                // Salvar frações do proprietário
                List<Fracao> fracoes = prop.getListaFracao();
                writer.println("FRACOES:" + fracoes.size());
                for (Fracao fracao : fracoes) {
                    writer.println(fracao.getIdentificador());
                }
                writer.println("FIM_PROPRIETARIO");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar proprietários: " + e.getMessage());
        }
    }

    // Método para carregar a lista de condomínios de um arquivo
    private static void carregarCondominios(List<Condominio> condominios) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONDOMINIOS_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.equals("CONDOMINIO")) {
                    carregarCondominio(reader, condominios);
                }
            }
        } catch (IOException e) {
            System.out.println("Aviso: Arquivo de condomínios não encontrado. Será criado ao salvar.");
        }
    }

    // Método para carregar um condomínio de um arquivo
    private static void carregarCondominio(BufferedReader reader, List<Condominio> condominios) throws IOException {
        int identificador = Integer.parseInt(reader.readLine());
        String morada = reader.readLine();
        double despesasGerais = Double.parseDouble(reader.readLine());
        double despesasElevador = Double.parseDouble(reader.readLine());
        LocalDate dataConstrucao = LocalDate.parse(reader.readLine());
        int maxFracoes = Integer.parseInt(reader.readLine());

        Condominio cond = new Condominio(morada, identificador, despesasGerais, 
                                       despesasElevador, dataConstrucao, maxFracoes);

        String linha = reader.readLine();
        int numFracoes = Integer.parseInt(linha.split(":")[1]);
        
        for (int i = 0; i < numFracoes; i++) {
            Fracao fracao = carregarFracao(reader);
            if (fracao != null) {
                cond.adicionarFracao(fracao);
            }
        }

        condominios.add(cond);
        reader.readLine();
    }

    // Método para carregar uma fração de um arquivo
    private static Fracao carregarFracao(BufferedReader reader) throws IOException {
        String tipo = reader.readLine();
        String identificador = reader.readLine();
        double area = Double.parseDouble(reader.readLine());
        double percentagemArea = Double.parseDouble(reader.readLine());
        String localizacao = reader.readLine();

        switch (tipo) {
            case "APARTAMENTO" -> {
                String tipoApt = reader.readLine();
                int numCasaBanho = Integer.parseInt(reader.readLine());
                int numVaranda = Integer.parseInt(reader.readLine());
                boolean temTerraco = Boolean.parseBoolean(reader.readLine());
                return new Apartamentos(tipoApt, numCasaBanho, numVaranda, temTerraco,
                        identificador, area, percentagemArea, localizacao);
            }

            case "LOJA" -> {
                return new Lojas(identificador, area, percentagemArea, localizacao);
            }

            case "GARAGEM" -> {
                int numViaturas = Integer.parseInt(reader.readLine());
                boolean temLavagem = Boolean.parseBoolean(reader.readLine());
                return new Garagens(numViaturas, temLavagem, identificador, area,
                        percentagemArea, localizacao);
            }

            case "ARRECADACAO" -> {
                boolean temPortaBlindada = Boolean.parseBoolean(reader.readLine());
                return new Arrecadacao(temPortaBlindada, identificador, area,
                        percentagemArea, localizacao);
            }

            default -> {
                System.out.println("Tipo de fração desconhecido: " + tipo);
                return null;
            }
        }
    }

    // Método para carregar a lista de proprietários de um arquivo
    private static void carregarProprietarios(List<Proprietario> proprietarios) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PROPRIETARIOS_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.equals("PROPRIETARIO")) {
                    carregarProprietario(reader, proprietarios);
                }
            }
        } catch (IOException e) {
            System.out.println("Aviso: Arquivo de proprietários não encontrado. Será criado ao salvar.");
        }
    }

    // Método para carregar um proprietário de um arquivo
    private static void carregarProprietario(BufferedReader reader, List<Proprietario> proprietarios) throws IOException {
        String identificador = reader.readLine();
        String nome = reader.readLine();
        String morada = reader.readLine();
        String telefone = reader.readLine();
        String telemovel = reader.readLine();
        String email = reader.readLine();
        LocalDate dataNascimento = LocalDate.parse(reader.readLine());

        Proprietario prop = new Proprietario(identificador, nome, morada, telefone, 
                                           telemovel, email, dataNascimento);

        // Carregar IDs das frações
        String linha = reader.readLine();
        int numFracoes = Integer.parseInt(linha.split(":")[1]);
        
        for (int i = 0; i < numFracoes; i++) {
            String fracaoId = reader.readLine();
        }

        proprietarios.add(prop);
        reader.readLine();
    }

}