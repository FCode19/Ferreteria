<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Listado de Vendedores</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body class="bg-light">
        <!-- Nav -->
        <%@include file="WEB-INF/jspf/nav.jspf" %>
        <div class="container mt-5">
            <h3 class="mb-4">Gestión de Usuarios</h3>

            <!-- Botón agregar -->
            <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#modalNuevo">
                Nuevo Usuario
            </button>

            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </c:if>

            <!-- Tabla de Vendedores -->
            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Usuario</th>
                        <th>Correo</th>
                        <th>Rol</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="v" items="${listaVendedores}">
                        <tr>
                            <td>${v.idVendedor}</td>
                            <td>${v.nombre}</td>
                            <td>${v.usuario}</td>
                            <td>${v.correo}</td>
                            <td>${v.rol.nombre}</td>
                            <td>
                                <span class="badge bg-${v.estado ? 'success' : 'secondary'}">
                                    ${v.estado ? 'Activo' : 'Inactivo'}
                                </span>
                            </td>
                            <td>
                                <a href="#" class="btn btn-sm btn-warning"
                                   data-bs-toggle="modal" data-bs-target="#modalEditar"
                                   data-id="${v.idVendedor}"
                                   data-nombre="${v.nombre}"
                                   data-usuario="${v.usuario}"
                                   data-correo="${v.correo}"
                                   data-estado="${v.estado}"
                                   data-idrol="${v.rol.idRol}">
                                    Editar
                                </a>

                                <c:if test="${v.rol.nombre ne 'Superadmin'}">
                                    <!-- Activar/Desactivar -->
                                    <form action="SrvVendedor" method="post" class="d-inline">
                                        <input type="hidden" name="accion" value="cambiarEstado" />
                                        <input type="hidden" name="id_vendedor" value="${v.idVendedor}" />
                                        <input type="hidden" name="estado" value="${!v.estado}" />
                                        <button type="submit" class="btn btn-sm btn-${v.estado ? 'secondary' : 'success'}">
                                            ${v.estado ? 'Desactivar' : 'Activar'}
                                        </button>
                                    </form>
                                    <!-- Eliminar -->
                                    <!--<a href="SrvVendedor?accion=eliminar&id=${v.idVendedor}" class="btn btn-sm btn-danger"
                                       onclick="return confirm('¿Desea eliminar este usuario?');">Eliminar</a>-->
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    
    <!-- Modal Agregar Vendedor -->
    <div class="modal fade" id="modalNuevo" tabindex="-1" aria-labelledby="modalNuevoLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="SrvVendedor" method="post">
                    <input type="hidden" name="accion" value="registrar">

                    <div class="modal-header">
                        <h5 class="modal-title" id="modalNuevoLabel">Registrar Nuevo Usuario</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>

                    <div class="modal-body">
                        <div class="mb-3">
                            <label>Nombre completo</label>
                            <input type="text" name="nombre" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label>Usuario</label>
                            <input type="text" name="usuario" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label>Correo</label>
                            <input type="email" name="correo" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label>Contraseña</label>
                            <input type="password" name="contraseña" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label>Rol</label>
                            <select name="id_rol" class="form-select" required>
                                <c:forEach var="r" items="${listaRoles}">
                                    <c:if test="${r.nombre ne 'Superadmin'}">
                                        <option value="${r.idRol}">${r.nombre}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success">Registrar</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <!-- Modal Editar Vendedor -->
    <div class="modal fade" id="modalEditar" tabindex="-1" aria-labelledby="modalEditarLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="SrvVendedor" method="post">
                    <input type="hidden" name="accion" value="modificar">
                    <input type="hidden" name="id_vendedor" id="edit-id">

                    <div class="modal-header">
                        <h5 class="modal-title" id="modalEditarLabel">Editar Usuario</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>

                    <div class="modal-body">
                        <div class="mb-3">
                            <label>Nombre</label>
                            <input type="text" name="nombre" id="edit-nombre" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label>Usuario</label>
                            <input type="text" name="usuario" id="edit-usuario" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label>Correo</label>
                            <input type="email" name="correo" id="edit-correo" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label>Rol</label>
                            <select name="id_rol" id="edit-idrol" class="form-select" required>
                                <c:forEach var="r" items="${listaRoles}">
                                    <c:if test="${r.nombre ne 'Superadmin'}">
                                        <option value="${r.idRol}">${r.nombre}</option>
                                    </c:if>
                                </c:forEach>
                            </select>

                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success">Actualizar</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- JS externo -->
    <script src="${pageContext.request.contextPath}/resources/js/vendedor-modal.js"></script>
</body>
</html>
