document.addEventListener("DOMContentLoaded", function () {
  const modalEditar = document.getElementById("modalEditar");

  if (modalEditar) {
    modalEditar.addEventListener("show.bs.modal", function (event) {
      const button = event.relatedTarget;
      document.getElementById("edit-id").value = button.getAttribute("data-id");
      document.getElementById("edit-nombre").value = button.getAttribute("data-nombre");
      document.getElementById("edit-usuario").value = button.getAttribute("data-usuario");
      document.getElementById("edit-correo").value = button.getAttribute("data-correo");
      document.getElementById("edit-idrol").value = button.getAttribute("data-idrol");
      document.getElementById("edit-estado").value = button.getAttribute("data-estado");

    });
  }
});

