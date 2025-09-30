<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.decotechnology.model.Producto" %>

<!DOCTYPE html>
<html>
<head>
    <title>Decotechnology - Lista de Productos</title>
    <style>
        /* Fuente y fondo general */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f4f8;
            margin: 0;
            padding: 20px;
            color: #333;
        }

        h1 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 30px;
        }

        /* Botones estilo neumórfico */
        .btn {
            padding: 8px 16px;
            border: none;
            border-radius: 12px;
            cursor: pointer;
            font-weight: bold;
            transition: all 0.2s ease;
            box-shadow: 3px 3px 6px #b8c6db, -3px -3px 6px #ffffff;
        }

        .btn-primary {
            background-color: #4caf50;
            color: #fff;
        }

        .btn-primary:hover {
            background-color: #45a049;
            box-shadow: inset 2px 2px 4px #b8c6db, inset -2px -2px 4px #ffffff;
        }

        .btn-edit {
            background-color: #2196f3;
            color: #fff;
        }

        .btn-edit:hover {
            background-color: #1e88e5;
            box-shadow: inset 2px 2px 4px #b8c6db, inset -2px -2px 4px #ffffff;
        }

        .btn-delete {
            background-color: #f44336;
            color: #fff;
        }

        .btn-delete:hover {
            background-color: #e53935;
            box-shadow: inset 2px 2px 4px #b8c6db, inset -2px -2px 4px #ffffff;
        }

        /* Tabla con estilo moderno */
        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0;
            margin-top: 20px;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            background-color: #ffffff;
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
        }

        th {
            background-color: #2c3e50;
            color: #fff;
            font-weight: 600;
        }

        tr:nth-child(even) {
            background-color: #f7f9fc;
        }

        tr:hover {
            background-color: #e1f5fe;
        }

        /* Enlace de nuevo producto */
        .top-action {
            display: flex;
            justify-content: flex-end;
            margin-bottom: 10px;
        }

    </style>
</head>
<body>
    <h1>Lista de Productos</h1>

    <div class="top-action">
        <a href="productos?action=nuevo" class="btn btn-primary">Nuevo producto</a>
    </div>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Categoria</th>
                <th>Modelo</th>
                <th>Proveedor</th>
                <th>Fecha de registro</th>
                <th>Costo</th>
                <th>Precio</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Producto> productos = (List<Producto>) request.getAttribute("productos"); 
                if (productos != null && !productos.isEmpty()) { 
                    for (Producto producto : productos) { 
            %>
            <tr>
                <td><%= producto.getId() %></td>
                <td><%= producto.getNombre() %></td>
                <td><%= producto.getCategoria() %></td>
                <td><%= producto.getModelo() %></td>
                <td><%= producto.getProveedor() %></td>
                <td><%= producto.getFechaRegistro() %></td>
                <td>$<%= producto.getCosto() %></td>
                <td>$<%= producto.getPrecio() %></td>
                <td>
                    <a href="productos?action=editar&id=<%= producto.getId() %>" class="btn btn-edit">Editar</a>
                    <a href="productos?action=eliminar&id=<%= producto.getId() %>" class="btn btn-delete" onclick="return confirm('¿Está seguro de eliminar este producto?')">Eliminar</a>
                </td>
            </tr>
            <% 
                    } 
                } else { 
            %>
            <tr>
                <td colspan="9" style="text-align: center;">No hay productos registrados</td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
