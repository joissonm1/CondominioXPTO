package projectofinal;

import java.time.LocalDate;


public class Main {

    
    public static void main(String[] args)
    {
        
        Proprietario proprietario = new Proprietario("P01S", "Joisson", "Luanda", "936390379", "923321252", "joisson.paulo@isp.com", LocalDate.of(1985, 5, 10));
        Fracao apartamento1 = new Apartamentos("T2", 2, 1, true, "AP01", 75, 15, "Andar 1");
        Fracao loja1 = new Lojas("L01", 100, 20, "Térreo");
        Arrecadacao arrecadacao = new Arrecadacao(true, "AR01", 155, 20, "Andar 10");
        
        proprietario.adicionarFracao(apartamento1);
        proprietario.adicionarFracao(loja1);
        proprietario.adicionarFracao(arrecadacao);
        proprietario.listarFracao();
   
        System.out.println("Removendo...");
        proprietario.removerFracao(apartamento1);

        proprietario.listarFracao();
        
        // ciacao do objecto Condominio
        Condominio condominio = new Condominio("C01",1,100.0,34.8,LocalDate.of(1982,3,1),2);
        
        condominio.adicionarFracao(loja1);
        condominio.adicionarFracao(apartamento1);
        condominio.infoCondominio();
        
        condominio.removerFracao("AR01");
        condominio.removerFracao("L01");
    }
    
}
