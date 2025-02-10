package condominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.InputValidator;

public class Proprietario {
    private String identificador;
    private String nome;
    private String morada;
    private String telefone;
    private String telemovel;
    private String email;
    private LocalDate dataNascimento;
    private List<Fracao> listaFracao;

    // Construtor da classe Proprietario
    public Proprietario(String identificador, String nome, String morada, String telefone, String telemovel, String email, LocalDate dataNascimento) {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("O identificador não pode estar vazio.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }
        if (morada == null || morada.trim().isEmpty()) {
            throw new IllegalArgumentException("A morada não pode estar vazia.");
        }
        if (!InputValidator.isValidPhoneNumber(telefone)) {
            throw new IllegalArgumentException("O telefone é inválido.");
        }
        if (!InputValidator.isValidPhoneNumber(telemovel)) {
            throw new IllegalArgumentException("O telemóvel é inválido.");
        }
        if (!InputValidator.isValidEmail(email)) {
            throw new IllegalArgumentException("O email é inválido.");
        }
        if (dataNascimento == null) {
            throw new IllegalArgumentException("A data de nascimento não pode estar vazia.");
        }

        this.identificador = identificador;
        this.nome = nome;
        this.morada = morada;
        this.telefone = telefone;
        this.telemovel = telemovel;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.listaFracao = new ArrayList<>();
    }

    // Retorna o identificador do proprietário
    public String getIdentificador() {
        return identificador;
    }

    // Define o identificador do proprietário
    public void setIdentificador(String identificador) {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("O identificador não pode estar vazio.");
        }
        this.identificador = identificador;
    }

    // Retorna o nome do proprietário
    public String getNome() {
        return nome;
    }

    // Define o nome do proprietário
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }
        this.nome = nome;
    }

    // Retorna a morada do proprietário
    public String getMorada() {
        return morada;
    }

    // Define a morada do proprietário
    public void setMorada(String morada) {
        if (morada == null || morada.trim().isEmpty()) {
            throw new IllegalArgumentException("A morada não pode estar vazia.");
        }
        this.morada = morada;
    }

    // Retorna o telefone do proprietário
    public String getTelefone() {
        return telefone;
    }

    // Define o telefone do proprietário
    public void setTelefone(String telefone) {
        if (!InputValidator.isValidPhoneNumber(telefone)) {
            throw new IllegalArgumentException("O telefone é inválido.");
        }
        this.telefone = telefone;
    }

    // Retorna o telemóvel do proprietário
    public String getTelemovel() {
        return telemovel;
    }

    // Define o telemóvel do proprietário
    public void setTelemovel(String telemovel) {
        if (!InputValidator.isValidPhoneNumber(telemovel)) {
            throw new IllegalArgumentException("O telemóvel é inválido.");
        }
        this.telemovel = telemovel;
    }

    // Retorna o email do proprietário
    public String getEmail() {
        return email;
    }

    // Define o email do proprietário
    public void setEmail(String email) {
        if (!InputValidator.isValidEmail(email)) {
            throw new IllegalArgumentException("O email é inválido.");
        }
        this.email = email;
    }

    // Retorna a data de nascimento do proprietário
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    // Define a data de nascimento do proprietário
    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new IllegalArgumentException("A data de nascimento não pode estar vazia.");
        }
        this.dataNascimento = dataNascimento;
    }

    // Retorna a lista de frações do proprietário
    public List<Fracao> getListaFracao() {
        return Collections.unmodifiableList(listaFracao);
    }

    // Adiciona uma fração à lista do proprietário
    public void adicionarFracao(Fracao fracao) {
        if (fracao == null) {
            throw new IllegalArgumentException("A fração não pode ser nula.");
        }
        listaFracao.add(fracao);
    }

    // Remove uma fração da lista do proprietário
    public void removerFracao(Fracao fracao) {
        if (fracao == null) {
            throw new IllegalArgumentException("A fração não pode ser nula.");
        }
        listaFracao.remove(fracao);
    }

    // Lista as frações do proprietário
    public void listarFracao() {
        for (Fracao fracao : listaFracao) {
            System.out.println(fracao.getIdentificador());
        }
    }
}