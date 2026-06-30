document.addEventListener('DOMContentLoaded', function () {
  document.querySelectorAll('.confirm-delete').forEach(function (link) {
    link.addEventListener('click', function (event) {
      if (!confirm('¿Está seguro de eliminar este registro?')) event.preventDefault();
    });
  });
});
