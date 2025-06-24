
package modelo.dao;

import java.sql.*;
import java.util.ArrayList;
import modelo.entidad.Rol;

public class DaoRol {

    public ArrayList<Rol> listar() {
        ArrayList<Rol> lista = new ArrayList<>();
        try {
            Connection cnx = Conexion.getConexion();
            String sql = "select id_rol, nombre from rol order by nombre";
            PreparedStatement pstm = cnx.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Rol r = new Rol();
                r.setIdRol(rs.getInt("id_rol"));
                r.setNombre(rs.getString("nombre"));
                lista.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
