package projectofinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Proprietario {
    private String identificador;
    private String nome;
    private String morada;
    private String telefone;
    private String telemovel;
    private String email;
    private LocalDate dataNascimento;
    private List<Fracao> listaFracao;

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

    // Getters and setters with validation
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("O identificador não pode estar vazio.");
        }
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        if (morada == null || morada.trim().isEmpty()) {
            throw new IllegalArgumentException("A morada não pode estar vazia.");
        }
        this.morada = morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (!InputValidator.isValidPhoneNumber(telefone)) {
            throw new IllegalArgumentException("O telefone é inválido.");
        }
        this.telefone = telefone;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        if (!InputValidator.isValidPhoneNumber(telemovel)) {
            throw new IllegalArgumentException("O telemóvel é inválido.");
        }
        this.telemovel = telemovel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!InputValidator.isValidEmail(email)) {
            throw new IllegalArgumentException("O email é inválido.");
        }
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new IllegalArgumentException("A data de nascimento não pode estar vazia.");
        }
        this.dataNascimento = dataNascimento;
    }

    public List<Fracao> getListaFracao() {
        return Collections.unmodifiableList(listaFracao);
    }

    public void adicionarFracao(Fracao fracao) {
        if (fracao == null) {
            throw new IllegalArgumentException("A fração não pode ser nula.");
        }
        listaFracao.add(fracao);
    }

    public void removerFracao(Fracao fracao) {
        if (fracao == null) {
            throw new IllegalArgumentException("A fração não pode ser nula.");
        }
        listaFracao.remove(fracao);
    }

    public void listarFracao() {
        for (Fracao fracao : listaFracao) {
            System.out.println(fracao.getIdentificador());
        }
    }
}