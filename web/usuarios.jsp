<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión de Vendedores</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body class="bg-light">

        <%@include file="WEB-INF/jspf/nav.jspf" %>
        <div class="container mt-5">
            <h1>Vendedores</h1>
            <a>Esta sección es solo para tipo de rol "SuperAdmin"</a>
            <form action="SrvVendedor" method="POST">
                <input type="hidden" name="accion" value="Ingresar"><br>
                <button type="submit" class="btn btn-success">Ingresar</button>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger mt-3">${error}</div>
                </c:if>
            </form>
            <a href="index.jsp" class="btn btn-secondary">Volver</a>
        </div>
    </body>
</html>