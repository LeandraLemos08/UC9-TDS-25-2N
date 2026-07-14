package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;


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
       
       String url = "jdbc:postgresql://localhost/Escola";
        String usuario = "postgres";
        String senha = "root";
        
         try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão realizada com sucesso!");
                        
            String sql = "INSERT INTO aluno(nome,turma,email) VALUES (?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, "Maria");
            stmt.setString(2, "Turma Y");
            stmt.setString(3, "Maria@email.com");

            int linhas = stmt.executeUpdate();

            System.out.println("Linhas inseridas: " + linhas);
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
                stmt.setString(2,"Turma 25 2N");
                stmt.setString(3,"hathawayofc@email.com");
                stmt.setInt(4, 8);
                
                int linhas = stmt.executeUpdate();
                System.out.println("Registros alterado: "+linhas);
                
                stmt.close();
                conexao.close();
                
            }
            catch (SQLException erro) {
                System.out.println("Erro ao alterar o aluno");
                System.out.println(erro.getMessage());
            }
    }
            
     public static void apagar(){
     
     String sql = """
                  DELETE FROM aluno
                  WHERE id = 
                  """
         ;
     
     try {
     
      Connection conexao = conectar();
      PreparedStatement stmt = conexao.prepareStatement(sql);
      stmt.setInt(1, 7);
      int linhas = stmt.executeUpdate();
      System.out.println("Registros apagados: ");
      stmt.close();
      conexao.close();
     } catch (SQLException erro) {
                System.out.println("Erro ao apagar o aluno");
                System.out.println(erro.getMessage());
            }
     }
}