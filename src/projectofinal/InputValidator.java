package projectofinal;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidator {

    public static String getInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int getValidInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            }
        }
    }

    public static double getValidDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número decimal.");
            }
        }
    }

    public static double getValidPercentage(Scanner scanner, String prompt) {
        while (true) {
            double value = getValidDouble(scanner, prompt);
            if (value >= 0 && value <= 100) {
                return value;
            } else {
                System.out.println("Entrada inválida. A percentagem deve estar entre 0 e 100.");
            }
        }
    }

    public static String getValidPhoneNumber(Scanner scanner, String prompt) {
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

    public static String getValidEmail(Scanner scanner, String prompt) {
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

    public static LocalDate getValidDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String dateStr = scanner.nextLine();
            try {
                return LocalDate.parse(dateStr);
            } catch (Exception e) {
                System.out.println("Data inválida. Use o formato AAAA-MM-DD.");
            }
        }
    }

    public static String getValidFracaoType(Scanner scanner, String prompt) {
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

    public static String getValidApartmentType(Scanner scanner, String prompt) {
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

    public static boolean getValidBoolean(Scanner scanner, String prompt) {
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

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^9\\d{8}$");
    }

    public static boolean isValidEmail(String email) {
        return email != null && Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(email).matches();
    }
}