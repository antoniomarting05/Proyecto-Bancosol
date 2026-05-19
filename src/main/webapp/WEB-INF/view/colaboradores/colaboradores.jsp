<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.ColaboradorEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.ColaboradorEntity" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.dto.Colaborador" %><%--
  Created by IntelliJ IDEA.
  User: marin
  Date: 20/04/2026
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Colaboradores</title>
    <link rel="stylesheet" href="/css/global.css" />
    <link rel="stylesheet" href="/css/colaboradores.css">
</head>
<body>
<%
    List<Colaborador> colaboradores = (List<Colaborador>) request.getAttribute("colaboradores");
%>
<jsp:include page="../shared/navbar.jsp"/>

<main>
    <div class="page-wrapper">
        <div class="left-column">
            <div class="table-container card">
                <table class="modernTable">
                    <thead>
                    <tr>
                        <th>Colaborador</th>
                        <th>Domicilio</th>
                        <th>Localidad</th>
                        <th>Colabora en</th>
                        <th>Coordinador</th>
                        <th>Contacto Principal</th>
                        <th>Observaciones</th>
                    </tr>
                    </thead>
                    <tbody id="table-body">
                        <%
                            for (Colaborador colaborador : colaboradores) {

                        %>
                        <tr data-id="<%=colaborador.getId()%>">
                            <td class="font-medium text-blue"><%=colaborador.getNombre()%></td>
                            <td><%=colaborador.getDomicilio()%></td>
                            <td><%=colaborador.getLocalidadSede().getNombre()%></td>
                            <td><%=colaborador.getColaboraEn().getNombre()%></td>
                            <td><%=colaborador.getCoordinador().getNombre()%></td>
                            <td><%=colaborador.getContactoPrincipal()%></td>
                            <td><%=colaborador.getObservaciones()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="right-column">
            <div class="side-panel-unified">
                <div id="info-container" class="card side-panel">
                </div>
                <div class="buttons-wrapper">
                    <jsp:include page="buttons-colaboradores.jsp"/>
                </div>
            </div>

        </div>
    </div>
</main>
<script>
    const table = document.querySelector("#table-body");
    const infoContainer = document.querySelector("#info-container");
    const rightColumn = document.querySelector(".right-column");

    let id = null;

    function fetchColaboradorData(){
        if(!id) return;

        const params = new URLSearchParams();
        params.append("id", id);

        fetch("/colaboradores/buscarColaborador", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: params.toString()
        })
            .then(response => response.text())
            .then(html => {
                infoContainer.innerHTML = html;
                rightColumn.classList.add("open");
            })
            .catch(error => console.error("Error: ", error))
    }

    table.addEventListener("click", (e) => {
        const row = e.target.closest("tr");
        if (!row) return;

        if(row.classList.contains("selected")) {
            row.classList.remove("selected");
            rightColumn.classList.remove("open");
            id = null;
            return;
        }

        table.querySelectorAll("tr").forEach(r => r.classList.remove("selected"));

        row.classList.add("selected");
        id = row.dataset.id;

        fetchColaboradorData();
    });

    infoContainer.addEventListener("click", (e) => {
        if (e.target.id === "cancel-button"){
            rightColumn.classList.remove("open");
            table.querySelectorAll("tr").forEach(r => r.classList.remove("selected"));
        }
    })
</script>
</body>
</html>
