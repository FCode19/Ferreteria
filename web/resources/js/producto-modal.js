document.addEventListener("DOMContentLoaded", () => {
    const editarBtns = document.querySelectorAll(".btn-editar");

    editarBtns.forEach(btn => {
        btn.addEventListener("click", () => {
            // Obtener los datos desde los atributos del botón
            const id = btn.getAttribute("data-id");
            const nombre = btn.getAttribute("data-nombre");
            const codigo = btn.getAttribute("data-codigo");
            const categoria = btn.getAttribute("data-categoria");
            const precio = btn.getAttribute("data-precio");
            const stock = btn.getAttribute("data-stock");
            const stockMinimo = btn.getAttribute("data-stockminimo");

            // Llenar el formulario del modal
            document.getElementById("edit-id").value = id;
            document.getElementById("edit-nombre").value = nombre;
            document.getElementById("edit-codigo").value = codigo;
            document.getElementById("edit-precio").value = precio;
            document.getElementById("edit-stock").value = stock;
            document.getElementById("edit-stock-minimo").value = stockMinimo;

            // Seleccionar la categoría correspondiente
            const selectCategoria = document.getElementById("edit-categoria");
            for (let i = 0; i < selectCategoria.options.length; i++) {
                if (selectCategoria.options[i].value === categoria) {
                    selectCategoria.selectedIndex = i;
                    break;
                }
            }

            // Mostrar el modal manualmente (por si Bootstrap no lo hace automáticamente)
            const modal = new bootstrap.Modal(document.getElementById("modalEditar"));
            modal.show();
        });
    });
});
