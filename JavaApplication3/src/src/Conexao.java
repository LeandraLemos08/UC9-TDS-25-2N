package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;

public class Conexao {
    
    public static Connection conectar() {
       Connection conexao = null;
       
       String url = "jdbc:postgresql://localhost/Escola";
        String usuario = "postgres";
        String senha = "root";
        
         try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão realizada com sucesso!");
      
        } catch (SQLException erro) {
            System.out.println("Erro ao conectar.");
            System.out.println(erro.getMessage());
        }
        
      return conexao;  
    }
    
    public static Connection Cadastrar(){
       Connection conexao = null;
       
       Scanner entrada = new Scanner(System.in);
       
       String url = "jdbc:postgresql://localhost/Escola";
        String usuario = "postgres";
        String senha = "root";
        
         try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão realizada com sucesso!");
            
            String sql = "INSERT INTO aluno(nome,turma,email) VALUES (?, ?, ?)";
            
                System.out.println("--------------------------");
                System.out.println("     Cadastro de Alunos   ");
                System.out.println("--------------------------");
                System.out.print(  "Digite o nome do aluno: ");
                System.out.print(  "Digite a turma do aluno: ");
                System.out.print(  "Digite o e-mail do aluno: ");
                        
            PreparedStatement stmt = conexao.prepareStatement(sql);

            int linhasCadastradas = stmt.executeUpdate();

            System.out.println("Linhas inseridas: " + linhasCadastradas);
            System.out.println("Aluno cadastrado com sucesso!");

            stmt.close();
            conexao.close();
       
        } catch (SQLException erro) {
            System.out.println("Erro ao conectar.");
            System.out.println(erro.getMessage());
        }
        
      return conexao;        
    }
    
    public static void alterar(){
        
            String sql = """
                         UPDATE aluno
                         Set nome = ?, turma = ?, email = ?
                         WHERE id = ?
                         """;
            try {
                Connection conexao = conectar();
                
                PreparedStatement stmt = conexao.prepareStatement(sql);
                
                stmt.setString(1,"Anne Hathaway");
                stmt.setString(2,"666");
                stmt.setString(3,"HataOfc@gmail.com");
                stmt.setInt(1, 8);
                
                int linhas = stmt.executeUpdate();
                System.out.println("Registros alterado: "+linhas);
                
                stmt.close();
                conexao.close();
                
            }
            catch (SQLException erro) {
                System.out.println("Erro ao alterar o aluno");
                System.out.println(erro.getMessage());
            }}
            
            public static void alterarNEW(){
                
                Scanner entrada = new Scanner(System.in);
 
            String sql = """
                         UPDATE aluno
                         Set nome = ?, turma = ?, email = ?
                         WHERE id = ?
                         """
                    ;
            try {
                Connection conexao = conectar();
                
                System.out.println("==========================");
                System.out.println("    Alteração de Alunos   ");
                System.out.println("==========================");
                
                System.out.print("Digite o id do aluno: ");
                int id = entrada.nextInt();
                entrada.nextLine();
                System.out.print("Novo nome: ");
                String nome = entrada.nextLine();
                System.out.print("Nova turma: ");
                String Turma = entrada.nextLine();
                System.out.print("Novo e-mail: ");
                String email = entrada.nextLine();
                
                PreparedStatement stmt = conexao.prepareStatement(sql);
                
                stmt.setString(1,nome);
                stmt.setString(2,Turma);
                stmt.setString(3,email);
                stmt.setInt(4, 8);
                
                int linhasAlteradas = stmt.executeUpdate();
                
              if (linhasAlteradas > 0) {
                
                System.out.println("------------------");
                System.out.println("ID: "+id);
                System.out.println("Nome: "+nome);
                System.out.println("Turma: "+Turma);
                System.out.println("E-mail: "+email);
             
              } else {
              
              System.out.println();
              System.out.println("Nenhum aluno encontrado com o id informado.");
              }}  catch (SQLException erro) {
                System.out.println("Erro ao alterar o aluno");
                System.out.println(erro.getMessage());
            }
    }    
                      
     public static void apagar(){
     
     Scanner entrada = new Scanner(System.in);
         
     String sql = """
                  DELETE FROM aluno
                  WHERE id = ?
                  """
         ;
     
     try {
     
      Connection conexao = conectar();
      
      System.out.print("Digite o id do aluno a apagar: ");
      int id = entrada.nextInt();
      
      
      PreparedStatement stmt = conexao.prepareStatement(sql);
      stmt.setInt(1, id);
      int linhasApagadas = stmt.executeUpdate();
      System.out.println("Registros apagados: "+linhasApagadas);
      stmt.close();
      conexao.close();
     } catch (SQLException erro) {
                System.out.println("Erro ao apagar o aluno");
                System.out.println(erro.getMessage());
            }
     }

      public static void buscarAluno(){
      
       Scanner entrada = new Scanner(System.in);
       
       System.out.print("Digite o id do aluno: ");
       int id = entrada.nextInt();
       
       String sql = "SELECT * FROM Aluno WHERE id = ";
       
       try {
       
       Connection conexao = Conexao.conectar();
       PreparedStatement stmt = conexao.prepareStatement(sql);
       stmt.setInt(1, id);
       
       ResultSet resultado = stmt.executeQuery();
       
       if(resultado.next()){
       
       System.out.println("Aluno encontrado");
         System.out.println("ID: "+resultado.getInt("ID"));
         System.out.println("Nome: "+resultado.getString("nome"));
         System.out.println("Turma: "+resultado.getString("Turma"));
         System.out.println("E-mail: "+resultado.getString("email"));
         
       } else {
           System.out.println("Aluno não encontrado.");
           } 
       resultado.close();
       stmt.close();
       conexao.close();
       } catch (SQLException erro){
           System.out.println("Erro ao buscar aluno.");
           System.out.println(erro.getMessage());
       }
    }
      
      public static void listarAlunos(){
      
      String sql = "SELECT * FROM Aluno ORDER BY id";
      try {
      
      Connection conexao = conectar();
      PreparedStatement stmt = conexao.prepareStatement(sql);
      ResultSet resultado = stmt.executeQuery();
      System.out.println();
      System.out.println("-----------------------");
      System.out.println("     Lista de Alunos   ");
      System.out.println("-----------------------");
       
      boolean encontrouAluno = false;
      while (resultado.next()){
          encontrouAluno = true;
          
         System.out.println("ID: " +resultado.getInt("ID"));
         System.out.println("Nome: "+resultado.getString("nome"));
         System.out.println("Turma: "+resultado.getString("Turma"));
         System.out.println("E-mail: "+resultado.getString("email"));
         System.out.println("-----------------------");   
      }} catch(SQLException erro){
           System.out.println("Erro ao buscar aluno.");
           System.out.println(erro.getMessage());
       }

      }
                   
      }

       
       
       
       
      
      


