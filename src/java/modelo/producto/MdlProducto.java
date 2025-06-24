package modelo.producto;

import java.util.ArrayList;
import modelo.dao.DaoProducto;
import modelo.entidad.Producto;

/**
 *
 * @author constantino.ramirez
 */
public class MdlProducto {

    private final DaoProducto dao = new DaoProducto();

    public ArrayList<Producto> listar() {
        return dao.listar();
    }

    public Producto buscar(int id) {
        return dao.buscar(id);
    }

    public void registrar(Producto p) {
        dao.registrar(p);
    }

    public void modificar(Producto p) {
        dao.modificar(p);
    }

    public void eliminar(int id) {
        dao.eliminar(id);
    }
}
