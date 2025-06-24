<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Ferretería Mi Perú</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    </head>
    <body>
        <!-- Nav -->
        <%@include file="WEB-INF/jspf/nav.jspf" %>

        <!-- SECTION BIENVENIDA -->
        <div class="container my-5 section-bienvenida">
            <div class="row align-items-center">
                <div class="col-md-8">
                    <h3 class="text-center text-md-start">Bienvenido a la ferretería Mi Perú</h3>
                    <p class="text-center text-md-start">
                        Encuentra herramientas, materiales y asesoría técnica para tus proyectos. Calidad garantizada.
                    </p>
                </div>
            </div>
        </div>

        <!-- CAROUSEL DE IMÁGENES -->
        <div class="container mb-5">
            <div id="carouselHerramientas" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="${pageContext.request.contextPath}/resources/img/herramienta1.jpg" class="d-block w-100 img-fluid" alt="Herramienta 1">
                    </div>
                    <div class="carousel-item">
                        <img src="${pageContext.request.contextPath}/resources/img/herramienta2.jpg" class="d-block w-100 img-fluid" alt="Herramienta 2">
                    </div>
                    <div class="carousel-item">
                        <img src="${pageContext.request.contextPath}/resources/img/herramienta3.jpg" class="d-block w-100 img-fluid" alt="Herramienta 3">
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselHerramientas" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselHerramientas" data-bs-slide="next">
                    <span class="carousel-control-next-icon"></span>
                </button>
            </div>
        </div>
    </div>

    <!-- FOOTER -->
    <footer class="mt-5 py-4 bg-dark text-white text-center">
        <p class="mb-1">&copy; 2025 Ferretería Mi Perú</p>
        <small>📍 Av. Los Constructores 123 - Lima | ☎ 999 888 777</small>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
