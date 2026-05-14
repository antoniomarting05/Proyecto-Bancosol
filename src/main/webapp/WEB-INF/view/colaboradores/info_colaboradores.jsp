<%@ page import="com.leftjoiners.bancosol.proyectobackend.dto.Colaborador" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.dto.ContactoColaborador" %><%--
  Created by IntelliJ IDEA.
  User: marin
  Date: 29/04/2026
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/colaboradores.css" />
</head>
<body>
<%Colaborador colaborador = (Colaborador) request.getAttribute("colaborador");%>

<div id="colaborador-container">
    <p>Colaborador seleccionado</p>
    <div id="colaborador-localization">
        <p id="lbl-colaborador"><%=colaborador.getNombre()%></p>
        <p id="lbl-domicilio"><%=colaborador.getDomicilio()%></p>
        <p class="text-mutex"><%=colaborador.getCodigo()%>, <%=colaborador.getLocalidadSede().getNombre()%></p>
        <p class="text-mutex">Colabora en: <%=colaborador.getColaboraEn().getNombre()%></p>
    </div>
    <div id="colaborador-schedule">
        <div id="contactosCard">
            <%for (ContactoColaborador c : colaborador.getContactos()){%>
                <div class="contacto">
                    <p>Nombre: <%=c.getNombre()%></p>
                    <p>Email: <%=c.getEmail()%></p>
                    <p>Teléfono: <%=c.getTelefono()%></p>
                </div>
            <%}%>
        </div>
        <div id="colaborador-observaciones">
            <p><%=colaborador.getObservaciones() != null && !colaborador.getObservaciones().isEmpty() ? colaborador.getObservaciones() : "Sin observaciones."%></p>
        </div>
    </div>
</div>
</body>
</html>
