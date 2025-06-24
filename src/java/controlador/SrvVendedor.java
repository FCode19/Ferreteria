
package controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import modelo.entidad.Rol;
import modelo.entidad.Vendedor;
import modelo.vendedor.MdlVendedor;

@WebServlet(name = "SrvVendedor", urlPatterns = {"/SrvVendedor"})
public class SrvVendedor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        MdlVendedor mv = new MdlVendedor();

        if (accion == null || accion.equals("listar")) {
            ArrayList<Vendedor> lista = mv.listar();
            request.setAttribute("listaVendedores", lista);
            request.setAttribute("listaRoles", new modelo.dao.DaoRol().listar());
            request.getRequestDispatcher("vendedor.jsp").forward(request, response);

        } else if (accion.equals("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            HttpSession sesion = request.getSession();
            Vendedor actual = (Vendedor) sesion.getAttribute("vendedor");

            if (actual != null && actual.getIdVendedor() == id) {
                request.setAttribute("error", "Usuario PROTEGIDO, No se puede Eliminar.");
                request.setAttribute("listaVendedores", mv.listar());
                request.setAttribute("listaRoles", new modelo.dao.DaoRol().listar());
                request.getRequestDispatcher("vendedor.jsp").forward(request, response);
                return;
            }

            mv.eliminar(id);
            response.sendRedirect("SrvVendedor?accion=listar");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession();
        Integer idRol = (Integer) session.getAttribute("id_rol");

        if (idRol == null || idRol != 1) {
            response.sendRedirect("index.jsp");
            return;
        }
        request.getRequestDispatcher("usuarios.jsp").forward(request, response);        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        MdlVendedor mv = new MdlVendedor();
        HttpSession session = request.getSession();
        Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");
        
        if (accion.equals("Ingresar")) {
            // Verificamos si el vendedor está logueado y su rol es 1 (SuperAdmin)
            if (vendedor != null && vendedor.getRol() != null && vendedor.getRol().getIdRol() == 1) {
                // Acceso permitido
                ArrayList<Vendedor> lista = mv.listar();
                request.setAttribute("listaVendedores", lista);
                request.setAttribute("listaRoles", new modelo.dao.DaoRol().listar());
                request.getRequestDispatcher("vendedor.jsp").forward(request, response);
            } else {
                // Acceso denegado
                request.setAttribute("error", "Acceso restringido: Solo el SuperAdmin puede ingresar.");
                request.getRequestDispatcher("usuarios.jsp").forward(request, response);
            }
            return;
        }

        if (accion.equals("registrar")) {
            Vendedor v = new Vendedor();
            v.setNombre(request.getParameter("nombre"));
            v.setUsuario(request.getParameter("usuario"));
            v.setCorreo(request.getParameter("correo"));
            v.setContraseña(request.getParameter("contraseña"));
            Rol r = new Rol();
            r.setIdRol(Integer.parseInt(request.getParameter("id_rol")));
            v.setRol(r);

            mv.registrar(v);

        } else if (accion.equals("modificar")) {
            Vendedor v = new Vendedor();
            v.setIdVendedor(Integer.parseInt(request.getParameter("id_vendedor")));
            v.setNombre(request.getParameter("nombre"));
            v.setUsuario(request.getParameter("usuario"));
            v.setCorreo(request.getParameter("correo"));
            Rol r = new Rol();
            r.setIdRol(Integer.parseInt(request.getParameter("id_rol")));
            v.setRol(r);
            mv.modificar(v);

        } else if (accion.equals("cambiarEstado")) {
            int id = Integer.parseInt(request.getParameter("id_vendedor"));
            boolean estado = Boolean.parseBoolean(request.getParameter("estado"));
            mv.cambiarEstado(id, estado);
        }

        request.setAttribute("listaVendedores", mv.listar());
        request.setAttribute("listaRoles", new modelo.dao.DaoRol().listar());
        request.getRequestDispatcher("vendedor.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "SrvVendedor - Gestión de usuarios";
    }
}
