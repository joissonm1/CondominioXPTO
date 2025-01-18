package condominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Condominio {
    private String morada;
    private int identificador;
    private double totalDespesasGerais;
    private double totalDespesasElevador;
    private LocalDate dataConstrucao;
    private int numeroFracoes;
    private int maxFracoes;
    private List<Fracao> listaFracao;

    public Condominio(String morada, int identificador, double totalDespesasGerais, double totalDespesasElevador, LocalDate dataConstrucao, int maxFracoes) {
        if (morada == null || morada.trim().isEmpty()) {
            throw new IllegalArgumentException("A morada não pode estar vazia.");
        }
        if (identificador <= 0) {
            throw new IllegalArgumentException("O identificador deve ser maior que zero.");
        }
        if (totalDespesasGerais < 0) {
            throw new IllegalArgumentException("As despesas gerais não podem ser negativas.");
        }
        if (totalDespesasElevador < 0) {
            throw new IllegalArgumentException("As despesas do elevador não podem ser negativas.");
        }
        if (dataConstrucao == null) {
            throw new IllegalArgumentException("A data de construção não pode estar vazia.");
        }
        if (dataConstrucao.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de construção não pode ser no futuro.");
        }
        if (maxFracoes <= 0) {
            throw new IllegalArgumentException("O número máximo de frações deve ser maior que zero.");
        }

        this.morada = morada;
        this.identificador = identificador;
        this.totalDespesasGerais = totalDespesasGerais;
        this.totalDespesasElevador = totalDespesasElevador;
        this.dataConstrucao = dataConstrucao;
        this.maxFracoes = maxFracoes;
        this.listaFracao = new ArrayList<>();
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

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        if (identificador <= 0) {
            throw new IllegalArgumentException("O identificador deve ser maior que zero.");
        }
        this.identificador = identificador;
    }

    public double getTotalDespesasGerais() {
        return totalDespesasGerais;
    }

    public void setTotalDespesasGerais(double totalDespesasGerais) {
        if (totalDespesasGerais < 0) {
            throw new IllegalArgumentException("As despesas gerais não podem ser negativas.");
        }
        this.totalDespesasGerais = totalDespesasGerais;
    }

    public double getTotalDespesasElevador() {
        return totalDespesasElevador;
    }

    public void setTotalDespesasElevador(double totalDespesasElevador) {
        if (totalDespesasElevador < 0) {
            throw new IllegalArgumentException("As despesas do elevador não podem ser negativas.");
        }
        this.totalDespesasElevador = totalDespesasElevador;
    }

    public LocalDate getDataConstrucao() {
        return dataConstrucao;
    }

    public void setDataConstrucao(LocalDate dataConstrucao) {
        if (dataConstrucao == null) {
            throw new IllegalArgumentException("A data de construção não pode estar vazia.");
        }
        if (dataConstrucao.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de construção não pode ser no futuro.");
        }
        this.dataConstrucao = dataConstrucao;
    }

    public int getNumeroFracoes() {
        return listaFracao.size();
    }

    public int getMaxFracoes() {
        return maxFracoes;
    }

    public List<Fracao> getListaFracao() {
        return listaFracao;
    }

    public void setListaFracao(List<Fracao> listaFracao) {
        if (listaFracao == null) {
            throw new IllegalArgumentException("A lista de frações não pode ser nula.");
        }
        this.listaFracao = listaFracao;
    }

    public void adicionarFracao(Fracao fracao) {
        if (fracao == null) {
            throw new IllegalArgumentException("A fração não pode ser nula.");
        }
        if (listaFracao.size() >= maxFracoes) {
            throw new IllegalStateException("O condomínio já atingiu o número máximo de frações.");
        }
        listaFracao.add(fracao);
        recalcularPercentagens();
    }

    public void removerFracao(String identificador) {
        listaFracao.removeIf(fracao -> fracao.getIdentificador().equals(identificador));
        recalcularPercentagens();
    }

    private void recalcularPercentagens() {
        double areaTotal = listaFracao.stream().mapToDouble(Fracao::getArea).sum();
        for (Fracao fracao : listaFracao) {
            fracao.setPercentagemArea((fracao.getArea() / areaTotal) * 100);
        }
    }

    public boolean verificarPercentagens() {
        double somaPercentagens = listaFracao.stream().mapToDouble(Fracao::getPercentagemArea).sum();
        return Math.abs(somaPercentagens - 100.0) < 0.001;
    }

    public double calcularSomaQuotasMensais() {
        return listaFracao.stream().mapToDouble(fracao -> fracao.calcularQuotaMensal(totalDespesasGerais, totalDespesasElevador)).sum();
    }

    public void infoCondominio() {
        System.out.println("Morada: " + morada);
        System.out.println("Identificador: " + identificador);
        System.out.println("Total Despesas Gerais: " + totalDespesasGerais);
        System.out.println("Total Despesas Elevador: " + totalDespesasElevador);
        System.out.println("Data de Construção: " + dataConstrucao);
        System.out.println("Número de Frações: " + getNumeroFracoes() + "/" + maxFracoes);
        System.out.println("Lista de Frações: ");
        for (Fracao fracao : listaFracao) {
            System.out.println(" - " + fracao.getIdentificador());
        }
    }
}