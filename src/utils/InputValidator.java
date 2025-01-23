//Classe para validação de entradas do usuário
public class InputValidator {

    private static final Scanner scanner = new Scanner(System.in);

    //Solicita uma entrada do usuário com uma mensagem de prompt
    public static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    //Solicita e valida um número inteiro positivo
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

    //Solicita e valida um número decimal não negativo
    public static double getValidDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine());
                if (value >= 0) { /
                    return value;
                } else {
                    System.out.println("Entrada inválida. Por favor, insira um número decimal maior ou igual a zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número decimal.");
            }
        }
    }

    //Solicita e valida uma percentagem entre 0 e 100
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

    //Solicita e valida um número de telefone no formato certo
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

    //Solicita e valida um endereço de email no formato certo
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

    //Solicita e valida uma data no formato AAAA-MM-DD, garantindo que não seja uma data no futuro
    public static LocalDate getValidDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String dateStr = scanner.nextLine();
            try {
                LocalDate date = LocalDate.parse(dateStr);
                if (date.isAfter(LocalDate.now())) {
                    System.out.println("Data inválida. A data de construção não pode ser no futuro.");
                } else {
                    return date;
                }
            } catch (Exception e) {
                System.out.println("Data inválida. Use o formato AAAA-MM-DD.");
            }
        }
    }

    // Solicita e valida o tipo de fração (apartamento, loja, garagem ou arrecadação)
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

    // Solicita e valida o tipo de apartamento (T0 a T5)
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

    // Solicita e valida um valor booleano (true ou false)
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

    // Verifica se o número de telefone está no formato correto (começa com 9 e tem 9 dígitos)
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^9\\d{8}$");
    }

    // Verifica se o email está no formato correto
    public static boolean isValidEmail(String email) {
        return email != null && Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(email).matches();
    }
}
