package condominio;

import condominio.Fracao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.InputValidator;

//Classe do Proprietario
public class Proprietario {
    private String identificador;
    private String nome;
    private String morada;
    private String telefone;
    private String telemovel;
    private String email;
    private LocalDate dataNascimento;
    private List<Fracao> listaFracao;

    //Construtor da classe Proprietario com validações
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

     //Retorna o identificador do Proprietario
    public String getIdentificador() {
        return identificador;
    }

    //Atualiza o identificador com validação
    public void setIdentificador(String identificador) {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("O identificador não pode estar vazio.");
        }
        this.identificador = identificador;
    }

    //Retorna o nome do Proprietario
    public String getNome() {
        return nome;
    }

    //Atualiza o nome com validação
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }
        this.nome = nome;
    }

    //Retorna a morada do Proprietario
    public String getMorada() {
        return morada;
    }

    //Atualiza a morada com validação
    public void setMorada(String morada) {
        if (morada == null || morada.trim().isEmpty()) {
            throw new IllegalArgumentException("A morada não pode estar vazia.");
        }
        this.morada = morada;
    }

    //Retorna o Telefone do Proprietario
    public String getTelefone() {
        return telefone;
    }

    //Atualiza o Telefone com validação
    public void setTelefone(String telefone) {
        if (!InputValidator.isValidPhoneNumber(telefone)) {
            throw new IllegalArgumentException("O telefone é inválido.");
        }
        this.telefone = telefone;
    }

    //Retorna o telemovel do Proprietario
    public String getTelemovel() {
        return telemovel;
    }

    //Atualiza o telemovel com validação
    public void setTelemovel(String telemovel) {
        if (!InputValidator.isValidPhoneNumber(telemovel)) {
            throw new IllegalArgumentException("O telemóvel é inválido.");
        }
        this.telemovel = telemovel;
    }

    //Retorna o email do Proprietario
    public String getEmail() {
        return email;
    }

    //Atualiza o email com validação
    public void setEmail(String email) {
        if (!InputValidator.isValidEmail(email)) {
            throw new IllegalArgumentException("O email é inválido.");
        }
        this.email = email;
    }

    //Retorna a data de nascimento do Proprietario
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    //Atualiza a data de nascimento com validação
    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new IllegalArgumentException("A data de nascimento não pode estar vazia.");
        }
        this.dataNascimento = dataNascimento;
    }
    
    //Retorna uma lista imutável das frações do condomínio
    public List<Fracao> getListaFracao() {
        return Collections.unmodifiableList(listaFracao);
    }

    //Adiciona uma fração à lista de frações do condomínio
    public void adicionarFracao(Fracao fracao) {
        if (fracao == null) {
            throw new IllegalArgumentException("A fração não pode ser nula.");
        }
        listaFracao.add(fracao);
    }
 
    //Remove uma fração à lista de frações do condomínio
    public void removerFracao(Fracao fracao) {
        if (fracao == null) {
            throw new IllegalArgumentException("A fração não pode ser nula.");
        }
        listaFracao.remove(fracao);
    }
    //Lista os identificadores de todas as frações no condomínio
    public void listarFracao() {
        for (Fracao fracao : listaFracao) {
            System.out.println(fracao.getIdentificador());
        }
    }
}
