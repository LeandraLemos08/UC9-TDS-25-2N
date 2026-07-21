
package src;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Aluno {
    
  public boolean cadastrarAluno(String nome, String turma, String email){
    
      String sql = "INSERT INTO aluno "
                    + "(nome, turma, email)"
                    + "VALUES (?, ?, ?)"
              ;
    
      try {
      
          Connection conexao = Conexao.conectar();
            
             if (conexao == null){
             System.out.println("Não foi possivel conectar!");
             return false;
          }
          
          PreparedStatement stmt = conexao.prepareStatement(sql);
          stmt.setString(1, nome);
          stmt.setString(2, turma);
          stmt.setString(3, email);
          
          int linhas = stmt.executeUpdate();
          stmt.close();
          conexao.close();
          
      } catch (SQLException erro) {
      
          
      
      }
      
    }
    
    
}
