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
        this.identificador = validarString(identificador, "O identificador não pode estar vazio.");
        this.nome = validarString(nome, "O nome não pode estar vazio.");
        this.morada = validarString(morada, "A morada não pode estar vazia.");
        this.telefone = validarTelefone(telefone, "O telefone não pode estar vazio ou inválido.");
        this.telemovel = validarTelefone(telemovel, "O telemóvel não pode estar vazio ou inválido.");
        this.email = validarEmail(email, "O email é inválido ou está vazio.");
        this.dataNascimento = validarDataNascimento(dataNascimento);
        this.listaFracao = new ArrayList<>();
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = validarDataNascimento(dataNascimento);
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = validarString(identificador, "O identificador não pode estar vazio.");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = validarString(nome, "O nome não pode estar vazio.");
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = validarString(morada, "A morada não pode estar vazia.");
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = validarTelefone(telefone, "O telefone não pode estar vazio ou inválido.");
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = validarTelefone(telemovel, "O telemóvel não pode estar vazio ou inválido.");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = validarEmail(email, "O email é inválido ou está vazio.");
    }

    public List<Fracao> getListaFracao() {
        return Collections.unmodifiableList(listaFracao); // Lista imutável para proteger o estado interno
    }

    public void setListaFracao(List<Fracao> listaFracao) {
        if (listaFracao == null) {
            throw new IllegalArgumentException("A lista de frações não pode ser nula.");
        }
        this.listaFracao = new ArrayList<>(listaFracao);
    }

    public void adicionarFracao(Fracao fracao) {
        if (fracao == null) {
            throw new IllegalArgumentException("A fração não pode ser nula.");
        }
        this.listaFracao.add(fracao);
    }

    public void removerFracao(Fracao fracao) {
        if (fracao == null) {
            throw new IllegalArgumentException("A fração não pode ser nula.");
        }
        if (!this.listaFracao.contains(fracao)) {
            throw new IllegalArgumentException("A fração não está associada a este proprietário.");
        }
        this.listaFracao.remove(fracao);
    }

    public void listarFracao() {
        if (listaFracao.isEmpty()) {
            System.out.println("Lista de frações do proprietário vazia!");
        } else {
            System.out.println("\n═══ Frações do Proprietário " + nome + " ═══");
            System.out.println();
            for (Fracao fracao : listaFracao) {
                System.out.println("Identificador: " + fracao.getIdentificador());
                System.out.println("Área: " + fracao.getArea());
                System.out.println("Percentagem: " + fracao.getPercentagemArea());
                System.out.println("Localização: " + fracao.getLocalizacao());
                System.out.println("════════════════════════════════════════════");
            }
        }
    }

    // Métodos auxiliares de validação
    private String validarString(String valor, String mensagemErro) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensagemErro);
        }
        return valor.trim();
    }

    private String validarTelefone(String telefone, String mensagemErro) {
        if (telefone == null || !telefone.matches("\\d{9,15}")) {
            throw new IllegalArgumentException(mensagemErro);
        }
        return telefone;
    }

    private String validarEmail(String email, String mensagemErro) {
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new IllegalArgumentException(mensagemErro);
        }
        return email;
    }

    private LocalDate validarDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento é inválida.");
        }
        return dataNascimento;
    }
}
