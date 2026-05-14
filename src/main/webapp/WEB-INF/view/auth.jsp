<%--
  Created by IntelliJ IDEA.
  User: javie
  Date: 14/04/2026
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inicie sesión</title>
</head>
<body>
<script src="https://cdn.tailwindcss.com"></script>
<div class="flex justify-center items-center h-full">
<div class="w-full max-w-sm bg-white border border-gray-200 p-8 rounded-lg">
    <!-- Título simple -->
    <h2 class="text-xl font-semibold text-gray-800 mb-6 text-center">Iniciar sesión</h2>

    <form action="/login" method="post" class="space-y-4">
        <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">Usuario</label>
            <input type="text" name="username" required
                   class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-1 focus:ring-black">
        </div>

        <!-- Campo Contraseña -->
        <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">Contraseña</label>
            <input type="password" name="password" required
                   class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-1 focus:ring-black">
        </div>

        <!-- Botón plano -->
        <button type="submit"
                class="w-full bg-black text-white py-2 rounded text-sm font-medium mt-2">
            Entrar
        </button>
    </form>

    <!-- Enlace simple -->
    <div class="mt-6 text-center">
        <a href="/" class="text-xs text-gray-500 hover:text-black">¿Olvidaste tu contraseña?</a>
    </div>
</div>
</div>
</body>
</html>
