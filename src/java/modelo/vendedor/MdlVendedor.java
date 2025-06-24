
package modelo.vendedor;

import java.util.ArrayList;
import modelo.dao.DaoVendedor;
import modelo.entidad.Vendedor;

public class MdlVendedor {

    private DaoVendedor dao = new DaoVendedor();

    public void registrar(Vendedor v) {
        dao.registrar(v);
    }

    public ArrayList<Vendedor> listar() {
        return dao.listar();
    }

    public Vendedor buscar(int id) {
        return dao.buscar(id);
    }

    public void modificar(Vendedor v) {
        dao.modificar(v);
    }

    public void eliminar(int id) {
        dao.eliminar(id);
    }
    
    public void cambiarEstado(int id, boolean estado) {
        dao.cambiarEstado(id, estado);
    }
}
