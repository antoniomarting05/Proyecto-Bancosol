<%@ page import="java.util.List" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.Cadena" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Gestión de Cadenas</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/campanyas.css">
    <link rel="stylesheet" href="/css/cadenas.css">
</head>


<body>
<%
    List<Cadena> listaCadenas = (List<Cadena>) request.getAttribute("cadenasSistema");
%>

<jsp:include page="../shared/navbar.jsp"/>

<main class="main-page">
    <section class="cadena-list-wrapper">

        <div class="cadena-list-header">
            <div class="cadena-list-title">
                <h2>Gestión de Cadenas</h2>
                <p>Modifica, añade u elimina datos de cadenas registradas en el sistema.</p>
            </div>

            <div class="cadena-header-actions">
                <a class="cadena-create-btn" href="/campanyas/crearCadena">
                    <span class="cadena-create-icon">+</span>
                    <span>Nueva cadena</span>
                </a>
            </div>
        </div>

        <form class="cadena-delete-form" action="/campanyas/eliminarCadenasSistema" method="post">

            <div class="card cadena-table-card">
                <table class="modernTable cadena-table">
                    <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Código</th>
                        <th>Acciones</th>
                    </tr>
                    </thead>

                    <tbody>
                    <% for (Cadena cadena : listaCadenas) { %>
                    <tr>
                        <td><%= cadena.getNombre() %></td>
                        <td><%= cadena.getCodigo() %></td>
                        <td>
                            <div class="cadena-row-actions">

                                <a class="cadena-edit-btn" href="/campanyas/editarCadena?id=<%= cadena.getId() %>">
                                    <span class="cadena-edit-icon">✎</span>
                                    <span>Editar</span>
                                </a>

                                <label class="cadena-delete-check">
                                    <input type="checkbox" name="cadenas" value="<%= cadena.getId() %>">
                                    <span>Seleccionar</span>
                                </label>

                            </div>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

            <div class="cadena-form-actions">
                <a class="btn-outline cadena-cancel-btn" href="/campanyas/gestionarCadenas">Cancelar cambios</a>

                <button class="cadena-delete-submit" type="submit">
                    Eliminar Seleccionadas
                </button>
            </div>

        </form>

    </section>
 </main>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const checkboxes = document.querySelectorAll(".cadena-delete-check input[type='checkbox']");

        checkboxes.forEach(function (checkbox) {
            const botonEliminar = checkbox.closest(".cadena-delete-check");
            const filaCadena = checkbox.closest("tr");

            function actualizarEstadoSeleccionado() {
                if (checkbox.checked) {
                    filaCadena.classList.add("cadena-row-selected");
                } else {
                    filaCadena.classList.remove("cadena-row-selected");
                }
            }

            actualizarEstadoSeleccionado();
            checkbox.addEventListener("change", actualizarEstadoSeleccionado);
        });
    });
</script>

</body>
</html>