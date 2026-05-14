<%@ page import="java.util.List" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.TiendaEntity" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.TiendaCampanyaEntity" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.CampanyaEntity" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.TiendaCampanyaEntity" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.TiendaEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestión de Tiendas | Bancosol</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/tiendas.css">
</head>
<body>
<%
    List<TiendaEntity> tiendas = (List<TiendaEntity>) request.getAttribute("tiendas");
    List<TiendaCampanyaEntity> tiendaCampanyas = (List<TiendaCampanyaEntity>)request.getAttribute("tiendaCampanyas");
%>

<jsp:include page="../shared/navbar.jsp"/>

<main>
    <div class="page-wrapper">

        <div class="left-column">

            <div class="header-actions">
                <div id="filtrado-tiendas">
                    <select name="cadena" id="cadena-tienda" class="btn-outline" style="padding: 5px 15px;">
                        <option value="">Filtrar por Cadena...</option>
                        <%-- Aquí iría tu bucle de cadenas --%>
                    </select>
                </div>
            </div>

            <section class="tiendas-container">
                <div class="table-container card">
                    <table class="modernTable">
                        <thead>
                        <tr>
                            <th rowspan="2">Tienda</th>
                            <th rowspan="2">Participa</th>
                            <th rowspan="2">Domicilio</th>
                            <th rowspan="2">Localidad</th>
                            <th colspan="2" style="text-align: center;">Coordinadores</th>
                        </tr>
                        <tr>
                            <th style="border-left: 1px solid rgba(255,255,255,0.1);">Primavera</th>
                            <th>Gran Recogida</th>
                        </tr>
                        </thead>

                        <tbody id="table-body-tiendas">
                        <% for(TiendaEntity tienda : tiendas){ %>
                        <tr data-id="<%=tienda.getId()%>">
                            <td class="font-medium text-blue"><%= tienda.getNombre() %></td>
                            <td style="text-align: center;">
                                <input type="checkbox" name="participa" value="<%=tienda.getId()%>">
                            </td>
                            <td class="small-td"><%= tienda.getDomicilio() %></td>
                            <td><%= tienda.getLocalidad().getNombre() %></td>

                            <%-- Primavera --%>
                            <% for(TiendaCampanyaEntity c : tienda.getTiendasCampanya()) { %>
                            <td>
                                <div style="display: flex; justify-content: space-between; align-items: center; gap: 5px;">
                                    <span class="small-td"><%= (c.getCampanya().getTipoCampanya().getId() == 2 ? c.getCoordinador().getNombre() : "Sin asignar") %></span>
                                    <button class="btn-outline" style="padding: 2px 8px; font-size: 0.7rem;">VER</button>
                                </div>
                            </td>
                            <%-- Gran Recogida --%>
                            <td>
                                <div style="display: flex; justify-content: space-between; align-items: center; gap: 5px;">
                                    <span class="small-td"><%= (c.getCampanya().getTipoCampanya().getId() == 1 ? c.getCoordinador().getNombre() : "Sin asignar") %></span>
                                    <button class="btn-outline" style="padding: 2px 8px; font-size: 0.7rem;">VER</button>
                                </div>
                            </td>
                            <% } %>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
            </section>
        </div>

        <div class="right-column">
            <div class="card side-panel" id="info-container-tienda">
            </div>
        </div>
    </div>
</main>

<jsp:include page="../shared/footer.jsp"/>

<script>
    const tableBody = document.querySelector("#table-body-tiendas");
    const rightColumn = document.querySelector(".right-column");
    const infoContainer = document.querySelector("#info-container-tienda");

    let idTiendaActual = null;
    infoContainer.innerHTML = "";

    // Función para pedir el HTML al servidor
    function fetchTiendaDetalle() {
        if (!idTiendaActual) return;

        // Petición al Servlet (puedes usar GET o POST según tu Java)
        fetch(`/tiendas/obtenerFormularioEdicion?id=${data-id}`)
            .then(response => {
                if (!response.ok) throw new Error("Error en la red");
                return response.text(); // Recibimos el HTML generado por el JSP/Servlet
            })
            .then(html => {
                // Inyectamos el HTML recibido
                infoContainer.innerHTML = html;
                // Abrimos el panel con la animación
                rightColumn.classList.add("open");
            })
            .catch(error => console.error("Error al cargar detalle:", error));
    }

    // Evento al hacer clic en la fila de la tabla
    tableBody.addEventListener("click", (e) => {
        const row = e.target.closest("tr");
        if (!row) return;

        // Si ya está seleccionada, cerramos
        if (row.classList.contains("selected")) {
            cerrarPanel();
            return;
        }

        // Marcar fila seleccionada
        tableBody.querySelectorAll("tr").forEach(r => r.classList.remove("selected"));
        row.classList.add("selected");

        // Guardar ID y pedir datos
        idTiendaActual = row.dataset.id;
        fetchTiendaDetalle();
    });

    // Función para cerrar y limpiar
    function cerrarPanel() {
        rightColumn.classList.remove("open");
        tableBody.querySelectorAll("tr").forEach(r => r.classList.remove("selected"));
        idTiendaActual = null;

        // Limpiamos el HTML después de que termine la animación de cierre (300ms)
        setTimeout(() => {
            infoContainer.innerHTML = "";
        }, 300);
    }

    // Delegación de eventos para botones inyectados (Cerrar y Guardar)
    infoContainer.addEventListener("click", (e) => {
        if (e.target.id === "cancel-button") {
            cerrarPanel();
        }

        if (e.target.id === "create-button") {
            // Aquí iría tu lógica para enviar el formulario
            console.log("Enviando cambios de la tienda:", idTiendaActual);
        }
    });
</script>

</body>
</html>