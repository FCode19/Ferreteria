
package modelo.dao;

import java.sql.*;
import java.util.*;
import modelo.entidad.Vendedor;
import modelo.entidad.Rol;


public class DaoVendedor implements IMantenimiento<Vendedor> {

    public ArrayList<Vendedor> listar() {
        ArrayList<Vendedor> lista = new ArrayList<>();
        try {
            Connection cnx = Conexion.getConexion();
            String sql = "SELECT v.id_vendedor, v.nombre, v.usuario, v.contraseña, v.correo, v.estado, " +
             "r.id_rol, r.nombre AS rol_nombre " +
             "FROM vendedor v INNER JOIN rol r ON v.id_rol = r.id_rol";

            PreparedStatement pstm = cnx.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Vendedor v = new Vendedor();
                v.setIdVendedor(rs.getInt("id_vendedor"));
                v.setNombre(rs.getString("nombre"));
                v.setUsuario(rs.getString("usuario"));
                v.setContraseña(rs.getString("contraseña"));
                v.setCorreo(rs.getString("correo"));
                v.setEstado(rs.getBoolean("estado"));   
                
                Rol r = new Rol();
                r.setIdRol(rs.getInt("id_rol"));
                r.setNombre(rs.getString("rol_nombre"));
                v.setRol(r);

                lista.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    @Override
    public void registrar(Vendedor v) {
        try {
            Connection cnx = Conexion.getConexion();
            String sql = "insert into vendedor (nombre, usuario, contraseña, correo, id_rol) values (?, ?, ?, ?, ?)";
            PreparedStatement pstm = cnx.prepareStatement(sql);
            pstm.setString(1, v.getNombre());
            pstm.setString(2, v.getUsuario());
            pstm.setString(3, v.getContraseña());
            pstm.setString(4, v.getCorreo());
            pstm.setInt(5, v.getRol().getIdRol());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vendedor buscar(int id) {
        Vendedor v = null;
        try {
            Connection cnx = Conexion.getConexion();
            String sql = "select v.id_vendedor, v.nombre, v.usuario, v.contraseña, v.correo, v.estado,"
                    + " r.id_rol, r.nombre as rol_nombre from"
                    + " vendedor v inner join rol r on v.id_rol = r.id_rol where v.id_vendedor = ? ";
            PreparedStatement pstm = cnx.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                v = new Vendedor();
                v.setIdVendedor(rs.getInt("id_vendedor"));
                v.setNombre(rs.getString("nombre"));
                v.setUsuario(rs.getString("usuario"));
                v.setContraseña(rs.getString("contraseña"));
                v.setCorreo(rs.getString("correo"));
                v.setEstado(rs.getBoolean("estado"));

                Rol r = new Rol();
                r.setIdRol(rs.getInt("id_rol"));
                r.setNombre(rs.getString("rol_nombre"));
                v.setRol(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public void modificar(Vendedor v) {
        String sql = "update vendedor set nombre=?, usuario=?, correo=?, id_rol=?, estado=? where id_vendedor=?";
        
        try (Connection cnx = Conexion.getConexion(); PreparedStatement pst = cnx.prepareStatement(sql)){
            
            PreparedStatement pstm = cnx.prepareStatement(sql);
            pstm.setString(1, v.getNombre());
            pstm.setString(2, v.getUsuario());
            pstm.setString(3, v.getContraseña());
            pstm.setString(4, v.getCorreo());
            pstm.setInt(5, v.getRol().getIdRol());
            pstm.setBoolean(6, v.isEstado());
            pstm.setInt(7, v.getIdVendedor());
            
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
        public void eliminar(int idVendedor) {
        try (Connection cnx = Conexion.getConexion()) {
            String check = "select r.protegido from vendedor v inner join"
                    + " rol r on v.id_rol = r.id_rol where v.id_vendedor = ?";
            try (PreparedStatement checkStmt = cnx.prepareStatement(check)) {
                checkStmt.setInt(1, idVendedor);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getBoolean("protegido")) {
                    System.out.println("No se puede eliminar un usuario con rol protegido.");
                    return;
                }
            }
            String sql = "update vendedor set estado = 0 where id_vendedor = ?";
            try (PreparedStatement pst = cnx.prepareStatement(sql)) {
                pst.setInt(1, idVendedor);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public void cambiarEstado(int idVendedor, boolean estado) {
        try (Connection cnx = Conexion.getConexion()) {
            String check = "select r.protegido from vendedor v inner join rol r "
                    + "on v.id_rol = r.id_rol where v.id_vendedor = ?";
            try (PreparedStatement checkStmt = cnx.prepareStatement(check)) {
                checkStmt.setInt(1, idVendedor);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getBoolean("protegido")) {
                    System.out.println("No se puede cambiar el estado de un usuario con rol protegido.");
                    return;
                }
            }
            String sql = "update vendedor set estado = ? where id_vendedor = ?";
            try (PreparedStatement pst = cnx.prepareStatement(sql)) {
                pst.setBoolean(1, estado);
                pst.setInt(2, idVendedor);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Vendedor validar(String usuario, String contraseña) {
        Vendedor v = null;
        try {
            Connection cnx = Conexion.getConexion();
            String sql = "select v.id_vendedor, v.nombre, v.usuario, v.correo,v.estado, r.id_rol,"
                    + " r.nombre as rol_nombre from vendedor v inner join rol r on"
                    + " v.id_rol = r.id_rol where v.usuario = ? and v.contraseña = ? and v.estado = 1";
            PreparedStatement pstm = cnx.prepareStatement(sql);
            pstm.setString(1, usuario);
            pstm.setString(2, contraseña);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                v = new Vendedor();
                v.setIdVendedor(rs.getInt("id_vendedor"));
                v.setNombre(rs.getString("nombre"));
                v.setUsuario(rs.getString("usuario"));
                v.setCorreo(rs.getString("correo"));
                v.setEstado(rs.getBoolean("estado"));

                Rol r = new Rol();
                r.setIdRol(rs.getInt("id_rol"));
                r.setNombre(rs.getString("rol_nombre"));
                v.setRol(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }
}

