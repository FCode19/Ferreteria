<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet" />
</head>
<body>

<div class="login-container">
    <div class="login-box">
        <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="Logo" class="logo">
        <h2 class="subtitle">Ferreteria MI PERÚ S.A.C</h2>

        <form action="SrvLogin" method="post">
            <label for="Usuario">Usuario</label>
            <input type="text" id="usuario" name="usuario" required />

            <label for="password">Password</label>
             <input type="password" id="password" name="password" required />

             <div class="form-options">
                 <a href="#" onclick="alert('Debe comunicarse con el administrador para recuperar su contraseña.'); return false;">
                     ¿Olvidaste tu contraseña?
                 </a>
             </div>

            <button type="submit">Log in</button>
        </form>
        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3">${error}</div>
        </c:if>
    </div>
</div>
<footer class="footer-login">
    <p>&copy; 2025 GRUPO VI UTP-DWI</p>
    <small>Todos los derechos reservados</small>
</footer>

</body>
</html>
