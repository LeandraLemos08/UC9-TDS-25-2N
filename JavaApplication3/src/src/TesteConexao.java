package src;

import java.util.Scanner;
import src.Conexao;

public class TesteConexao {
    
     
    public static void main(String[] args) {
        
      Conexao.apagar();
    
    Scanner entrada = new Scanner(System.in);
    System.out.print("Digite o id do aluno: ");
    int id = entrada.nextInt(); 
    entrada.nextLine();
    System.out.println();
    System.out.println(" =================================== ");
    System.out.println("     Alteração de Dados do Aluno     ");
    System.out.println(" =================================== ");
    System.out.println(" Novo nome: ");
    System.out.println(" Nova turma: ");
    System.out.println(" Novo email: ");
    System.out.println(" =================================== ");
    entrada.close();
    }
}