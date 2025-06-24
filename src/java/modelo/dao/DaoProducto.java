
package modelo.dao;

import java.sql.*;
import java.util.ArrayList;
import modelo.entidad.Categoria;
import modelo.entidad.Producto;


public class DaoProducto implements IMantenimiento<Producto>{

    public ArrayList<Producto> listar() {
        ArrayList<Producto> lista = new ArrayList<>();
        String sql = "select p.*, c.nombre_categoria from producto p "
                + "join categoria c ON p.id_categoria = c.id_categoria order by 1";

        try (Connection cn = Conexion.getConexion(); PreparedStatement pst = cn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Categoria cat = new Categoria(rs.getInt("id_categoria"), rs.getString("nombre_categoria"));

                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setCodigo(rs.getString("codigo"));
                p.setCategoria(cat);
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setStockMinimo(rs.getInt("stock_minimo"));

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void registrar(Producto p) {
        String sql = "insert into producto(nombre, codigo, id_categoria, precio, stock, stock_minimo) values (?, ?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.getConexion(); PreparedStatement pst = cn.prepareStatement(sql)) {

            pst.setString(1, p.getNombre());
            pst.setString(2, p.getCodigo());
            pst.setInt(3, p.getCategoria().getIdCategoria());
            pst.setDouble(4, p.getPrecio());
            pst.setInt(5, p.getStock());
            pst.setInt(6, p.getStockMinimo());

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modificar(Producto p) {
        String sql = "update producto set nombre=?, codigo=?, id_categoria=?, precio=?, stock=?, stock_minimo=? WHERE id_producto=?";

        try (Connection cn = Conexion.getConexion(); PreparedStatement pst = cn.prepareStatement(sql)) {

            pst.setString(1, p.getNombre());
            pst.setString(2, p.getCodigo());
            pst.setInt(3, p.getCategoria().getIdCategoria());
            pst.setDouble(4, p.getPrecio());
            pst.setInt(5, p.getStock());
            pst.setInt(6, p.getStockMinimo());
            pst.setInt(7, p.getIdProducto());

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int idProducto) {
        String sql = "delete from producto where id_producto=?";

        try (Connection cn = Conexion.getConexion(); PreparedStatement pst = cn.prepareStatement(sql)) {

            pst.setInt(1, idProducto);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Producto buscar(int idProducto) {
        String sql = "SELECT p.*, c.nombre_categoria FROM producto p JOIN categoria c ON p.id_categoria = c.id_categoria WHERE p.id_producto = ?";
        Producto p = null;

        try (Connection cn = Conexion.getConexion(); PreparedStatement pst = cn.prepareStatement(sql)) {

            pst.setInt(1, idProducto);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Categoria cat = new Categoria(rs.getInt("id_categoria"), rs.getString("nombre_categoria"));

                p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setCodigo(rs.getString("codigo"));
                p.setCategoria(cat);
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setStockMinimo(rs.getInt("stock_minimo"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;
    }
}
