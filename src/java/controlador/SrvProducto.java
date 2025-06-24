package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import modelo.dao.DaoCategoria;
import modelo.entidad.Categoria;
import modelo.entidad.Producto;
import modelo.producto.MdlProducto;

@WebServlet(name = "SrvProducto", urlPatterns = {"/SrvProducto"})
public class SrvProducto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        MdlProducto mdl = new MdlProducto();
        DaoCategoria daoCat = new DaoCategoria();

        if (accion == null || accion.equals("listar")) {
            int pagina = 1;
            int filasPorPagina = 10;

            if (request.getParameter("pagina") != null) {
                try {
                    pagina = Integer.parseInt(request.getParameter("pagina"));
                } catch (NumberFormatException e) {
                    pagina = 1;
                }
            }

            ArrayList<Producto> listaCompleta = mdl.listar();
            int totalProductos = listaCompleta.size();

            int inicio = (pagina - 1) * filasPorPagina;
            int fin = Math.min(inicio + filasPorPagina, totalProductos);

            ArrayList<Producto> productosPaginados = new ArrayList<>(listaCompleta.subList(inicio, fin));

            request.setAttribute("lstProductos", productosPaginados);
            request.setAttribute("lstCategorias", daoCat.listar());
            request.setAttribute("pagina", pagina);
            request.setAttribute("totalPaginas", (int) Math.ceil((double) totalProductos / filasPorPagina));

            request.getRequestDispatcher("producto.jsp").forward(request, response);

        } else if (accion.equals("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            mdl.eliminar(id);
            response.sendRedirect("SrvProducto?accion=listar");

        } else {
            response.sendRedirect("SrvProducto?accion=listar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        MdlProducto mdl = new MdlProducto();

        // Crear objeto Producto
        Producto p = new Producto();
        p.setNombre(request.getParameter("nombre"));
        p.setCodigo(request.getParameter("codigo"));
        p.setPrecio(Double.parseDouble(request.getParameter("precio")));
        p.setStock(Integer.parseInt(request.getParameter("stock")));
        p.setStockMinimo(Integer.parseInt(request.getParameter("stock_minimo")));

        Categoria c = new Categoria();
        c.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
        p.setCategoria(c);

        if (accion.equals("registrar")) {
            mdl.registrar(p);
        } else if (accion.equals("modificar")) {
            p.setIdProducto(Integer.parseInt(request.getParameter("id")));
            mdl.modificar(p);
        }

        response.sendRedirect("SrvProducto?accion=listar");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
