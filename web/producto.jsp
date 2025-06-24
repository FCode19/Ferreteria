<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Ferretería Mi Perú</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    </head>
    <body class="bg-light">  
        <!-- Nav -->
        <%@include file="WEB-INF/jspf/nav.jspf" %>
        
        <div class="container mt-5">
            <h3 class="mb-4">Gestión de Productos</h3>
            <!-- Botón agregar -->
            <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#modalAgregar">
             Agregar Producto</button>
            <!-- Tabla de productos -->
            <table class="table table-bordered table-striped shadow">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Código</th>
                        <th>Categoría</th>
                        <th>Precio</th>
                        <th>Stock</th>
                        <th>Stock Mínimo</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="prod" items="${lstProductos}">
                        <tr>
                            <td>${prod.idProducto}</td>
                            <td>${prod.nombre}</td>
                            <td>${prod.codigo}</td>
                            <td>${prod.categoria.nombreCategoria}</td>
                            <td>S/ <fmt:formatNumber value="${prod.precio}" type="number" minFractionDigits="2"/></td>
                            <td>${prod.stock}</td> 
                            </td>
                            <td>${prod.stockMinimo}</td>
                            <td>
                                <button class="btn btn-info btn-sm btn-editar"
                                        data-id="${prod.idProducto}"
                                        data-nombre="${prod.nombre}"
                                        data-codigo="${prod.codigo}"
                                        data-precio="${prod.precio}"
                                        data-stock="${prod.stock}"
                                        data-stockminimo="${prod.stockMinimo}"
                                        data-categoria="${prod.categoria.idCategoria}">
                                    Editar
                                </button>
                                <a href="SrvProducto?accion=eliminar&id=${prod.idProducto}" class="btn btn-danger btn-sm"
                                   onclick="return confirm('¿Eliminar este producto?');">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="d-flex justify-content-center mt-3">
                <nav>
                    <ul class="pagination">
                        <c:forEach var="i" begin="1" end="${totalPaginas}">
                            <li class="page-item ${i == pagina ? 'active' : ''}">
                                <a class="page-link" href="SrvProducto?accion=listar&pagina=${i}">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>

        </div>

        <!-- Modal Agregar -->
        <div class="modal fade" id="modalAgregar" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="SrvProducto" method="post">
                        <input type="hidden" name="accion" value="registrar" />
                        <div class="modal-header">
                            <h5 class="modal-title">Agregar Producto</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label>Nombre</label>
                                <input type="text" name="nombre" class="form-control" required />
                            </div>
                            <div class="mb-3">
                                <label>Código</label>
                                <input type="text" name="codigo" class="form-control" required />
                            </div>
                            <div class="mb-3">
                                <label>Categoría</label>
                                <select name="categoria" class="form-select" required>
                                    <c:forEach var="cat" items="${lstCategorias}">
                                        <option value="${cat.idCategoria}">${cat.nombreCategoria}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label>Precio</label>
                                <input type="number" name="precio" step="0.01" class="form-control" required />
                            </div>
                            <div class="mb-3">
                                <label>Stock</label>
                                <input type="number" name="stock" class="form-control" required />
                            </div>
                            <div class="mb-3">
                                <label>Stock mínimo</label>
                                <input type="number" name="stock_minimo" class="form-control" required />
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-success" type="submit">Registrar</button>
                            <button class="btn btn-secondary" data-bs-dismiss="modal" type="button">Cancelar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Modal Editar -->
        <div class="modal fade" id="modalEditar" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="SrvProducto" method="post">
                        <input type="hidden" name="accion" value="modificar" />
                        <input type="hidden" name="id" id="edit-id" />
                        <div class="modal-header">
                            <h5 class="modal-title">Editar Producto</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label>Nombre</label>
                                <input type="text" name="nombre" id="edit-nombre" class="form-control" required />
                            </div>
                            <div class="mb-3">
                                <label>Código</label>
                                <input type="text" name="codigo" id="edit-codigo" class="form-control" required />
                            </div>
                            <div class="mb-3">
                                <label>Categoría</label>
                                <select name="categoria" id="edit-categoria" class="form-select" required>
                                    <c:forEach var="cat" items="${lstCategorias}">
                                        <option value="${cat.idCategoria}">${cat.nombreCategoria}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label>Precio</label>
                                <input type="number" name="precio" id="edit-precio" step="0.01" class="form-control" required />
                            </div>
                            <div class="mb-3">
                                <label>Stock</label>
                                <input type="number" name="stock" id="edit-stock" class="form-control" required />
                            </div>
                            <div class="mb-3">
                                <label>Stock mínimo</label>
                                <input type="number" name="stock_minimo" id="edit-stock-minimo" class="form-control" required />
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-success" type="submit">Guardar cambios</button>
                            <button class="btn btn-secondary" data-bs-dismiss="modal" type="button">Cancelar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- JS externo -->
        <script src="${pageContext.request.contextPath}/resources/js/producto-modal.js"></script>
    </body>
</html>
