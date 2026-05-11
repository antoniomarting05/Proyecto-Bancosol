
<%@ page import="java.util.List" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.Tienda" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.Campanya" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.Cadena" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.CadenaCampanya" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Gestión de Campañas</title>
        <link rel="stylesheet" href="/css/global.css">
        <link rel="stylesheet" href="/css/campanyas.css">
    </head>
    <body>
    <%
        List<Campanya> listaCampanyas = (List<Campanya>) request.getAttribute("campanyas");
    %>

    <jsp:include page="../shared/navbar.jsp"/>

    <main class="campanya-page">
        <section class="campanya-list-wrapper">
            <div class="campanya-header">
                <div>
                    <h1>Gestión de Campañas</h1>
                    <p>Consulta las campañas creadas y genera nuevas campañas.</p>
                </div>
            </div>

            <div class="card campanya-table-card">
                <table class="modernTable">
                    <thead>
                        <tr>
                            <th>Tipo Campaña</th>
                            <th>Nombre</th>
                            <th>Fecha Inicio</th>
                            <th>Fecha Fin</th>
                            <th>Cadenas participantes</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Campanya campanya : listaCampanyas) { %>
                        <tr>
                            <td><%= campanya.getTipoCampanya().getNombre()%></td>
                            <td><%= campanya.getNombre() %></td>
                            <td><%= campanya.getFechaInicio().toString() %></td>
                            <td><%= campanya.getFechaFin().toString() %></td>
                            <td>
                                <% for (CadenaCampanya c: campanya.getCadenasCampanya()){%>
                                    <span class="cadena-chip"><%=c.getCadena().getNombre()%></span>
                                <%};%>
                            </td>
                            <td>
                                <a class="action-link" href="/campanyas/editarCampanya?id=<%=campanya.getId()%>">Editar</a>
                            </td>
                        </tr>
                        <% }; %>
                    </tbody>
                </table>
            </div>

            <div class="campanya-list-actions">
                <a href="/campanyas/crearCampanya" class="btn-primary">Generar nueva Campaña</a>
            </div>
        </section>
    </main>


    </body>
</html>