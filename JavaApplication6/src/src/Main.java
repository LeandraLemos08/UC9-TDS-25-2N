
package src;
import java.util.Scanner;
public class Main {
    public static void main (String[]args){
    
    Scanner entrada = new Scanner(System.in);
    System.out.print("Digite o nome do aluno: ");
    String nome = entrada.nextLine();
    System.out.print("Digite a idade do aluno: ");
    int idade = entrada.nextInt();
    entrada.nextLine();
    System.out.print("Digite a turma do aluno: ");
    String Turma = entrada.nextLine();
    System.out.println();
    System.out.println(" ======================= ");
    System.out.println("     Dados do Aluno     ");
    System.out.println(" ======================= ");
    System.out.println(" Nome: "+nome);
    System.out.println(" Idade: "+idade+" anos");
    System.out.println(" Turma: "+Turma);
    System.out.println(" ======================= ");
    entrada.close();
    
    }
}
