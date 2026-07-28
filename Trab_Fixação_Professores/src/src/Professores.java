package src;
import src.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Professores {

    private int id;
    private String nome;
    private String disciplina;
    private String email;
    private String telefone;

    // Construtor vazio
    public Professores () {
    }

    // Construtor completo
    public Professores (int id, String nome, String disciplina, String email, String telefone) {
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

    public boolean cadastrarProfessores () {
        String sql = "INSERT INTO professor (nome, disciplina, email, telefone) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, this.nome);
            stmt.setString(2, this.disciplina);
            stmt.setString(3, this.email);
            stmt.setString(4, this.telefone);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException error) {
            System.out.println("Erro ao cadastrar professor: " + error.getMessage());
            return false;
        }

        con.close();
        stmt.close();
    }

    public ArrayList<Professores> listar() {
        ArrayList<Professores> professores = new ArrayList<>();
        String sql = "SELECT * FROM professor ORDER BY id";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Professores p = new Professores(
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

   
    public Professores localizarPorId(int id) {
        String sql = "SELECT * FROM Professores WHERE id = ?";

          Connection conexao = Conexao.conectar();
           if(conexao == null ){
           return null;
           }
           
           PreparedStatement stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, id);
           
           ResultSet resultado = stmt.executeQuery();
        
         if(resultado.next()){
            Professores professor = new Professores();
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

    // Métodos alterar e excluir: apenas declarados, sem implementação nesta etapa.

    public boolean alterar() {
        // Função de alteração será desenvolvida na próxima etapa.
        return false;
    }

    public boolean excluir() {
        // Função de exclusão será desenvolvida na próxima etapa.
        return false;
    }
}
