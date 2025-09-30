package com.decotechnology.controller;

import com.decotechnology.dao.ProductoDAO;
import com.decotechnology.model.Producto;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/productos")
public class ProductoController extends HttpServlet {

    private ProductoDAO productoDAO;

    @Override
    public void init() {
        productoDAO = new ProductoDAO(); // Inicializa el DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }

        switch (action) {
            case "nuevo":
                mostrarFormulario(request, response);
                break;
            case "editar":
                mostrarFormularioEdicion(request, response);
                break;
            case "eliminar":
                eliminarProducto(request, response);
                break;
            default:
                listarProductos(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("insertar".equals(action)) {
            insertarProducto(request, response);
        } else if ("actualizar".equals(action)) {
            actualizarProducto(request, response);
        }
    }

    // Listar productos
    private void listarProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Producto> productos = productoDAO.listarProductos();
        request.setAttribute("productos", productos);
        request.getRequestDispatcher("/lista-productos.jsp").forward(request, response);
    }

    // Mostrar formulario para nuevo producto
    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/formulario-producto.jsp").forward(request, response);
    }

    // Mostrar formulario para edición de producto
    private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Producto producto = productoDAO.obtenerProductoPorId(id);
        request.setAttribute("producto", producto);
        request.getRequestDispatcher("/formulario-producto.jsp").forward(request, response);
    }

    // Insertar producto
private void insertarProducto(HttpServletRequest request, HttpServletResponse response)
        throws IOException {

    Producto producto = new Producto();
    producto.setNombre(request.getParameter("nombre"));
    producto.setCategoria(request.getParameter("categoria"));
    producto.setModelo(request.getParameter("modelo"));
    producto.setProveedor(request.getParameter("proveedor"));
    producto.setFechaRegistro(request.getParameter("fechaRegistro")); // Formato YYYY-MM-DD
    producto.setCosto(Double.parseDouble(request.getParameter("costo")));
    producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
    producto.setDescripcion(request.getParameter("descripcion"));
    producto.setStock(Integer.parseInt(request.getParameter("stock")));

    productoDAO.insertarProducto(producto);
    response.sendRedirect("productos");
}


    // Actualizar producto
private void actualizarProducto(HttpServletRequest request, HttpServletResponse response)
        throws IOException {

    Producto producto = new Producto();
    producto.setId(Integer.parseInt(request.getParameter("id")));
    producto.setNombre(request.getParameter("nombre"));
    producto.setCategoria(request.getParameter("categoria"));
    producto.setModelo(request.getParameter("modelo"));
    producto.setProveedor(request.getParameter("proveedor"));
    producto.setFechaRegistro(request.getParameter("fechaRegistro")); // Formato YYYY-MM-DD
    producto.setCosto(Double.parseDouble(request.getParameter("costo")));
    producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
    producto.setDescripcion(request.getParameter("descripcion"));
    producto.setStock(Integer.parseInt(request.getParameter("stock")));

    productoDAO.actualizarProducto(producto);
    response.sendRedirect("productos");
}


    // Eliminar producto
    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        productoDAO.eliminarProducto(id);
        response.sendRedirect("productos");
    }
}
