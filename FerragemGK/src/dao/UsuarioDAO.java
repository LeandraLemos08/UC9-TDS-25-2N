
package dao;

import conexao.Conexao;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario autenticar(
            String login,
            String senha
    ) {

        String sql =
                "SELECT id_usuario, nome, login, nivel, ativo "
                + "FROM usuario "
                + "WHERE login = ? "
                + "AND senha = ? "
                + "AND ativo = TRUE";

        try (
                Connection conexao = Conexao.conectar();

                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Usuario usuario = new Usuario();

                    usuario.setIdUsuario(
                            rs.getLong("id_usuario")
                    );

                    usuario.setNome(
                            rs.getString("nome")
                    );

                    usuario.setLogin(
                            rs.getString("login")
                    );

                    usuario.setNivel(
                            rs.getString("nivel")
                    );

                    usuario.setAtivo(
                            rs.getBoolean("ativo")
                    );

                    return usuario;
                }
            }

        } catch (SQLException erro) {

            throw new RuntimeException(
                    "Erro ao autenticar usuário.",
                    erro
            );
        }

        return null;
    }
}