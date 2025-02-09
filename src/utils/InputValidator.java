package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidator {

    private static final Scanner scanner = new Scanner(System.in);

    // Obtém uma entrada de texto do usuário
    public static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Obtém um número inteiro válido do usuário
    public static int getValidInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Entrada inválida. Por favor, insira um número inteiro maior que zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            }
        }
    }

    // Obtém um número decimal válido do usuário
    public static double getValidDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine());
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println("Entrada inválida. Por favor, insira um número decimal maior ou igual a zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número decimal.");
            }
        }
    }

    // Obtém uma percentagem válida do usuário
    public static double getValidPercentage(String prompt) {
        while (true) {
            double value = getValidDouble(prompt);
            if (value >= 0 && value <= 100) {
                return value;
            } else {
                System.out.println("Entrada inválida. A percentagem deve estar entre 0 e 100.");
            }
        }
    }

    // Obtém um número de telefone válido do usuário
    public static String getValidPhoneNumber(String prompt) {
        while (true) {
            System.out.print(prompt);
            String phoneNumber = scanner.nextLine();
            if (isValidPhoneNumber(phoneNumber)) {
                return phoneNumber;
            } else {
                System.out.println("Número de telefone inválido. Deve começar com 9 e ter 9 dígitos.");
            }
        }
    }

    // Obtém um email válido do usuário
    public static String getValidEmail(String prompt) {
        while (true) {
            System.out.print(prompt);
            String email = scanner.nextLine();
            if (isValidEmail(email)) {
                return email;
            } else {
                System.out.println("Email inválido. Tente novamente.");
            }
        }
    }

    // Obtém uma data de nascimento válida do usuário
    public static LocalDate getValidNascimentoData(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        while (true) {
            System.out.print(prompt);
            String dateStr = scanner.nextLine().trim();
            
            try {
                LocalDate date = LocalDate.parse(dateStr, formatter);
                LocalDate hoje = LocalDate.now();
                
                // Validação para data de nascimento (mínimo 18 anos)
                LocalDate dataMinimaNascimento = hoje.minusYears(18);
                if (date.isAfter(dataMinimaNascimento)) {
                    System.out.println("Erro: Data de nascimento deve ser anterior a " + 
                        dataMinimaNascimento.format(displayFormatter));
                    continue;
                }
                
                // Validação para idade máxima (120 anos)
                LocalDate dataMaximaAntiga = hoje.minusYears(120);
                if (date.isBefore(dataMaximaAntiga)) {
                    System.out.println("Erro: Data de nascimento não pode ser anterior a " + 
                        dataMaximaAntiga.format(displayFormatter));
                    continue;
                }
                
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido! Use: AAAA-MM-DD (Ex: 2000-05-30)");
            }
        }
    }

    // Obtém uma data de construção válida do usuário
    public static LocalDate getValidConstrucaoData(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        while (true) {
            System.out.print(prompt);
            String dateStr = scanner.nextLine().trim();
            
            try {
                LocalDate date = LocalDate.parse(dateStr, formatter);
                LocalDate hoje = LocalDate.now();
                if (!date.isAfter(hoje)) {
                    System.out.println("Erro: Data de construção tem que ser no futuro. Por favor, insira uma data após " +
                        hoje.format(displayFormatter));
                    continue;
                }
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido! Use: AAAA-MM-DD (Ex: 2000-05-30)");
            }
        }
    }

    // Obtém um tipo de fração válido do usuário
    public static String getValidFracaoType(String prompt) {
        while (true) {
            System.out.print(prompt);
            String tipo = scanner.nextLine().toLowerCase();
            if (tipo.equals("apartamento") || tipo.equals("loja") || tipo.equals("garagem") || tipo.equals("arrecadacao")) {
                return tipo;
            } else {
                System.out.println("Tipo de fração inválido. Deve ser Apartamento, Loja, Garagem ou Arrecadacao.");
            }
        }
    }

    // Obtém um tipo de apartamento válido do usuário
    public static String getValidApartmentType(String prompt) {
        while (true) {
            System.out.print(prompt);
            String tipoApt = scanner.nextLine().toUpperCase();
            if (tipoApt.matches("T[0-5]")) {
                return tipoApt;
            } else {
                System.out.println("Tipo de apartamento inválido. Deve ser T0, T1, T2, T3, T4 ou T5.");
            }
        }
    }

    // Obtém um valor booleano válido do usuário
    public static boolean getValidBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String boolStr = scanner.nextLine().toLowerCase();
            if (boolStr.equals("true") || boolStr.equals("false")) {
                return Boolean.parseBoolean(boolStr);
            } else {
                System.out.println("Entrada inválida. Por favor, insira true ou false.");
            }
        }
    }

    // Verifica se um número de telefone é válido
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^9\\d{8}$");
    }

    // Verifica se um email é válido
    public static boolean isValidEmail(String email) {
        return email != null && Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(email).matches();
    }
}