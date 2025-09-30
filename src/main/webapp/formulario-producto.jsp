<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.decotechnology.model.Producto" %>

<%
    Producto producto = (Producto) request.getAttribute("producto");
    boolean esEdicion = (producto != null);
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= esEdicion ? "Editar Producto" : "Nuevo Producto" %></title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f4f8;
            margin: 0;
            padding: 40px 20px;
            color: #333;
        }

        h1 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 30px;
        }

        form {
            max-width: 600px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 16px;
            box-shadow: 6px 6px 12px #b8c6db, -6px -6px 12px #ffffff;
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        label {
            font-weight: 600;
            margin-bottom: 5px;
        }

        input, select, textarea {
            padding: 10px;
            border-radius: 12px;
            border: none;
            background-color: #f0f4f8;
            box-shadow: inset 3px 3px 6px #b8c6db, inset -3px -3px 6px #ffffff;
            font-size: 14px;
            width: 100%;
            transition: all 0.2s ease;
        }

        input:focus, select:focus, textarea:focus {
            outline: none;
            box-shadow: inset 2px 2px 4px #a1b0c2, inset -2px -2px 4px #ffffff;
        }

        button {
            padding: 12px;
            background-color: #4caf50;
            color: white;
            border: none;
            border-radius: 16px;
            cursor: pointer;
            font-weight: bold;
            box-shadow: 3px 3px 6px #b8c6db, -3px -3px 6px #ffffff;
            transition: all 0.2s ease;
            margin-top: 10px;
        }

        button:hover {
            background-color: #45a049;
            box-shadow: inset 2px 2px 4px #b8c6db, inset -2px -2px 4px #ffffff;
        }
    </style>
</head>
<body>
    <h1><%= esEdicion ? "Editar Producto" : "Nuevo Producto" %></h1>

    <form action="productos" method="post">
        <input type="hidden" name="action" value="<%= esEdicion ? "actualizar" : "insertar" %>">
        <% if (esEdicion) { %>
            <input type="hidden" name="id" value="<%= producto.getId() %>">
        <% } %>

        <label>Nombre:</label>
        <input type="text" name="nombre" value="<%= esEdicion ? producto.getNombre() : "" %>" required>

        <label>Categoría:</label>
        <input type="text" name="categoria" value="<%= esEdicion ? producto.getCategoria() : "" %>" required>

        <label>Modelo:</label>
        <input type="text" name="modelo" value="<%= esEdicion ? producto.getModelo() : "" %>">

        <label>Proveedor:</label>
        <input type="text" name="proveedor" value="<%= esEdicion ? producto.getProveedor() : "" %>">

        <label>Fecha de registro:</label>
        <input type="date" name="fechaRegistro" value="<%= esEdicion ? producto.getFechaRegistro() : "" %>">

        <label>Costo:</label>
        <input type="number" step="0.01" name="costo" value="<%= esEdicion ? producto.getCosto() : "" %>">

        <label>Precio:</label>
        <input type="number" step="0.01" name="precio" value="<%= esEdicion ? producto.getPrecio() : "" %>">

        <label>Descripción:</label>
        <textarea name="descripcion" rows="3"><%= esEdicion ? producto.getDescripcion() : "" %></textarea>

        <label>Stock:</label>
        <input type="number" name="stock" value="<%= esEdicion ? producto.getStock() : "" %>">

        <button type="submit"><%= esEdicion ? "Actualizar" : "Guardar" %></button>
    </form>
</body>
</html>
