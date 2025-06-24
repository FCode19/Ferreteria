package modelo.dao;

import java.sql.*;
import java.util.ArrayList;
import modelo.entidad.Categoria;

/**
 *
 * @author constantino.ramirez
 */
public class DaoCategoria {

    public ArrayList<Categoria> listar() {
        ArrayList<Categoria> lista = new ArrayList<>();
        String sql = "SELECT id_categoria, nombre_categoria FROM categoria";

        try (Connection cn = Conexion.getConexion(); PreparedStatement pst = cn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombreCategoria(rs.getString("nombre_categoria"));
                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
