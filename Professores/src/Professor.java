
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.DriverManager;

public class Professor {

    private int id;
    private String nome;
    private String disciplina;
    private String email;
    private String telefone;

    // Construtor vazio
    public Professor () {
    }

    // Construtor completo
    public Professor (int id, String nome, String disciplina, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.disciplina = disciplina;
        this.email = email;
        this.telefone = telefone;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean cadastrarProfessores (String nome, String disciplina, String email, String telefone) {
        String sql = "INSERT INTO professores (nome, disciplina, email, telefone) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, this.nome);
            stmt.setString(2, this.disciplina);
            stmt.setString(3, this.email);
            stmt.setString(4, this.telefone);

            int linhasAfetadas = stmt.executeUpdate();
            stmt.close();
            con.close();
            return linhasAfetadas > 0;

        } catch (SQLException error) {
            System.out.println("Erro ao cadastrar professor: " + error.getMessage());
            return false;
        }
    }

    public ArrayList<Professor> listar() {
        ArrayList<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professores ORDER BY id";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Professor p = new Professor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("disciplina"),
                        rs.getString("email"),
                        rs.getString("telefone")
                );
                professores.add(p);
            }

           rs.close();
           stmt.close();
           con.close();
            
        } catch (SQLException error) {
            System.out.println("Erro ao listar professores: " + error.getMessage());
        }

        return professores;
    }

   
    public Professor localizarPorId(int id) {
        String sql = "SELECT * FROM Professores WHERE id = ?";
        Professor professor = null;
        
        try {
          Connection conexao = Conexao.conectar();
           if(conexao == null ){
           return null;
           }
           
           PreparedStatement stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, id);
           
           ResultSet resultado = stmt.executeQuery();
        
         if(resultado.next()){
            professor = new Professor();
            professor.setId(resultado.getInt(id));
            professor.setNome(resultado.getString("Nome"));
            professor.setDisciplina(resultado.getString("disciplina"));
            professor.setEmail(resultado.getString("email"));
            professor.setTelefone(resultado.getString("telefone"));
           }
           
           resultado.close();
           stmt.close();
           conexao.close();

        } catch (SQLException error) {
            System.out.println("Erro ao localizar professor: " + error.getMessage());
        }
        return null;
    }
    

    public ArrayList<Professor> localizar(String tipo, String valor){
    
        ArrayList<Professor> lista = new ArrayList<>();
        
        String sql;
        
        switch (tipo){
            
            case"ID":
                sql = """
                      SELECT id, nome, disciplina, email, telefone FROM professores
                      WHERE id = ?
                      ORDER BY id
                      """;
                break;
                
            case "Nome":
                sql = """
                      SELECT id, nome, disciplina, email, telefone  FROM professores
                      WHERE nome ILIKE ?
                      ORDER BY disciplina, nome
                      """;
                break;
            case "Disciplina":
                sql = """
                      SELECT id, nome, disciplina, email, telefone FROM professores
                      WHERE turma ILIKE ?
                      ORDER BY disciplina, nome
                      """;
            break ;
            
            case "E-mail":
                sql = """
                      SELECT id, nome, disciplina, email, telefone FROM professores
                      WHERE email ILIKE ?
                      ORDER BY email
                      """;
                break;
                
                case "Telefone":
                sql = """
                      SELECT id, nome, disciplina, email, telefone FROM professores
                      WHERE email ILIKE ?
                      ORDER BY telefone
                      """;
                break;
                
            default: return lista;    
        }
        
        try {
            
            Connection conexao = Conexao.conectar();
            
            if (conexao == null) {
                return lista;
            }
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            if (tipo.equals("Id")) {
               int id = Integer.parseInt(valor);
               stmt.setInt(1,id);
            } else {
               stmt.setString(1, "%" + valor + "%");
            }
            
            ResultSet resultado = stmt.executeQuery();
            
            while (resultado.next()){
                Professor professor = new Professor();
                professor.setId(resultado.getInt("id"));
                professor.setNome(resultado.getString("nome"));
                professor.setDisciplina(resultado.getString("disciplina"));
                professor.setEmail(resultado.getString("email"));
                professor.setTelefone(resultado.getString("telefone"));

                lista.add(professor);
            }
            resultado.close();
            stmt.close();
            conexao.close();
        } 
        
        catch ( NumberFormatException erro){
              System.out.println("O ID deve conter apenas números");
        } 
        
        catch (SQLException erro) {
            System.out.println("Erro ao localizar aluno: " + erro.getMessage());
        } 
        return lista;
    }

  public boolean alterar(int id, String nome, String disciplina, String email, String telefone){
    String sql = "UPDATE professores"
                 + "SET nome = ?, disciplina = ?, email = ? , telefone = ? "
                 + "WHERE id = ? "
            ;
    try {
     Connection con = Conexao.conectar();
     if (con == null){
     return false;
}
     
     PreparedStatement stmt = con.prepareStatement(sql);
     stmt.setString(1, nome);
     stmt.setString(2, disciplina);
     stmt.setString(3, email);
     stmt.setString(4, telefone);
     stmt.setInt(5, id);
     
     int linhasAlteradas = stmt.executeUpdate();
     stmt.close();
     con.close();
     return linhasAlteradas > 0;
 }
    catch (SQLException error) {
         System.out.println("Erro ao alterar professor!" + error.getMessage());
 } 
    return false;
 }
   
   
   public boolean excluir(int id){
      String sql = "DELETE FROM professores WHERE id = ? "
              ;
       try {
     Connection con = Conexao.conectar();
     if (con == null){
     return false;
     }
       PreparedStatement stmt = con.prepareStatement(sql);
       stmt.setInt(1, id);
       
       int linhasExcluidas = stmt.executeUpdate();
       
       stmt.close();
       con.close();
       
       return linhasExcluidas > 0;
       
  }   catch(SQLException error){
        System.out.println("Erro ao excluir o Professor." + error.getMessage());
  }
 
       return false;
   
   }
   
}