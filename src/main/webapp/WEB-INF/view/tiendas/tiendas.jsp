<%@ page import="java.util.List" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.TiendaCampanyaEntity" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.TiendaEntity" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.CadenaEntity" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.ZonaEntity" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.LocalidadEntity" %>
<%@ page import="com.leftjoiners.bancosol.proyectobackend.entity.ColaboradorEntity" %>
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
    //List<TiendaCampanyaEntity> tiendaCampanyas = (List<TiendaCampanyaEntity>)request.getAttribute("tiendaCampanyas");

    List<CadenaEntity> cadenas = (List<CadenaEntity>) request.getAttribute("cadenas");
    List<ZonaEntity> zonas = (List<ZonaEntity>) request.getAttribute("zonas");
    List<LocalidadEntity> localidades = (List<LocalidadEntity>) request.getAttribute("localidades");
    //List<ColaboradorEntity> colaboradores = (List<ColaboradorEntity>) request.getAttribute("colaboradores"); //COORDINADORES VIENE DE AQUI? O NO??

%>

<jsp:include page="../shared/navbar.jsp"/>

<main>
    <div class="page-wrapper">

        <div class="left-column">
            <div>
                <h1>Gestión de Tiendas</h1>
                <p>Consulta, filtra y crea tiendas</p>
            </div>

            <div class="header-actions">


                <form id="filtrado-tiendas" action="/tiendas/filtrarTiendas" method="post">

                    <div class="filtro-group">
                        <label for="cadena-tienda">Filtrar por Cadena</label>
                        <select name="cadena-tienda" id="cadena-tienda" class="btn-outline" style="padding: 5px 15px;">
                            <option value="">Sin Filtro</option>
                            <% for(CadenaEntity c : cadenas){ %>
                            <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
                            <% } %>
                        </select>

                    </div>

                    <div class="filtro-group">
                        <label for="zona-tienda">Filtrar por Zona</label>
                        <select name="zona-tienda" id="zona-tienda" class="btn-outline" style="padding: 5px 15px;">
                            <option value="">Sin Filtro</option>
                            <% for(ZonaEntity z : zonas){ %>
                            <option value="<%=z.getId()%>"><%=z.getNombre()%></option>
                            <% } %>
                        </select>

                    </div>

                    <div class="filtro-group">
                        <label for="localidad-tienda">Filtrar por Localidad</label>
                        <select name="localidad-tienda" id="localidad-tienda" class="btn-outline" style="padding: 5px 15px;">
                            <option value="">Sin Filtro</option>
                            <% for(LocalidadEntity l : localidades){ %>
                            <option value="<%=l.getId()%>"><%=l.getNombre()%></option>
                            <% } %>
                        </select>
                    </div>




                    <div class="filtro-group boton-container">
                        <span class="label-spacer"></span>
                        <button type="submit" class="btn-outline btn-filtrar">Filtrar</button>
                    </div>
                </form>
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
                                <input type="checkbox" name="participa" value="<%=tienda.getId()%>">    <%-- checked si participa en tiendaCampanya? --%>
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


</body>
</html>