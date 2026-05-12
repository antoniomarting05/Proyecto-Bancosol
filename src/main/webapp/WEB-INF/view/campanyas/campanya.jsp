
<%@ page import="java.util.List" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.Tienda" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.Campanya" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.Cadena" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Gestión de Campañas</title>
        <link rel="stylesheet" href="/css/global.css">
        <link rel="stylesheet" href="/css/campanyas.css">
        <link rel="stylesheet" href="/css/cadenas.css">
    </head>
    <body>
    <%
        List<Campanya> listaCampanyas = (List<Campanya>) request.getAttribute("campanyas");
    %>

    <jsp:include page="../shared/navbar.jsp"/>

    <main class="main-page">
        <section class="campanya-list-wrapper">
            <div class="campanya-header">
                <div>
                    <h1>Gestión de Campañas</h1>
                    <p>Consulta las campañas creadas y genera nuevas campañas.</p>
                </div>
                <div class="campanya-list-actions">
                    <a class="btn-outline" href="/campanyas/gestionarCadenas">Gestionar Cadenas</a>
                    <a href="/campanyas/crearCampanya" class="btn-primary">
                        <span class="cadena-create-icon">+</span>
                        <span> Nueva Campaña</span>
                    </a>
                </div>
            </div>


            <div class="card campanya-table-card">
                <table class="modernTable">
                    <thead>
                        <tr>
                            <th></th>
                            <th>Tipo de campaña</th>
                            <th>Nombre</th>
                            <th>Fecha inicio</th>
                            <th>Fecha fin</th>
                            <th>Cadenas participantes</th>

                        </tr>
                    </thead>
                    <tbody>
                        <% for(Campanya campanya : listaCampanyas) { %>
                        <tr>
                            <td>
                                <a class="edit-campanya-btn" href="/campanyas/editarCampanya?id=<%=campanya.getId()%>">
                                    <span class="edit-campanya-icon">✎</span>
                                    <span>Editar</span>
                                </a>
                            </td>
                            <td><%= campanya.getTipoCampanya().getNombre()%></td>
                            <td><%= campanya.getNombre() %></td>
                            <td><%= campanya.getFechaInicio().toString() %></td>
                            <td><%= campanya.getFechaFin().toString() %></td>
                            <td>
                                <% for (Cadena c: campanya.getCadenasParticipantes()){%>
                                    <span class="cadena-chip"><%=c.getNombre()%></span>
                                <%};%>
                                <%=campanya.getCadenasParticipantes().isEmpty() ? "Sin cadenas participantes" : ""%>
                            </td>

                        </tr>
                        <% }; %>
                    </tbody>
                </table>
            </div>


        </section>
    </main>


    </body>
</html>