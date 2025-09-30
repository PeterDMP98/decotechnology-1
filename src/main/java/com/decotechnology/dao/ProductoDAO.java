package com.decotechnology.dao;

import com.decotechnology.model.Producto;
import com.decotechnology.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // Crear (INSERT)
    public void insertarProducto(Producto producto) {
        String sql = "INSERT INTO producto (nombre, categoria, modelo, proveedor, fecha_registro, costo, precio, descripcion, stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getCategoria());
            statement.setString(3, producto.getModelo());
            statement.setString(4, producto.getProveedor());
            statement.setString(5, producto.getFechaRegistro());
            statement.setDouble(6, producto.getCosto());
            statement.setDouble(7, producto.getPrecio());
            statement.setString(8, producto.getDescripcion());
            statement.setInt(9, producto.getStock());

            statement.executeUpdate();
            System.out.println("✅ Producto insertado correctamente");
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar producto");
            e.printStackTrace();
        }
    }

    // Leer todos (SELECT *)
    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setId(resultSet.getInt("id_producto"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setCategoria(resultSet.getString("categoria"));
                producto.setModelo(resultSet.getString("modelo"));
                producto.setProveedor(resultSet.getString("proveedor"));
                producto.setFechaRegistro(resultSet.getString("fecha_registro"));
                producto.setCosto(resultSet.getDouble("costo"));
                producto.setPrecio(resultSet.getDouble("precio"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setStock(resultSet.getInt("stock"));

                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener productos");
            e.printStackTrace();
        }
        return productos;
    }

    // Actualizar (UPDATE)
    public void actualizarProducto(Producto producto) {
        String sql = "UPDATE producto SET nombre=?, categoria=?, modelo=?, proveedor=?, fecha_registro=?, costo=?, precio=?, descripcion=?, stock=? WHERE id_producto=?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getCategoria());
            statement.setString(3, producto.getModelo());
            statement.setString(4, producto.getProveedor());
            statement.setString(5, producto.getFechaRegistro());
            statement.setDouble(6, producto.getCosto());
            statement.setDouble(7, producto.getPrecio());
            statement.setString(8, producto.getDescripcion());
            statement.setInt(9, producto.getStock());
            statement.setInt(10, producto.getId());

            statement.executeUpdate();
            System.out.println("✅ Producto actualizado correctamente");
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar producto");
            e.printStackTrace();
        }
    }

    // Eliminar (DELETE)
    public void eliminarProducto(int id_producto) {
        String sql = "DELETE FROM producto WHERE id_producto=?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id_producto);
            statement.executeUpdate();
            System.out.println("✅ Producto eliminado correctamente");
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar producto");
            e.printStackTrace();
        }
    }

    // Leer por ID (SELECT WHERE)
    public Producto obtenerProductoPorId(int id_producto) {
        Producto producto = null;
        String sql = "SELECT * FROM producto WHERE id_producto=?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id_producto);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    producto = new Producto();
                    producto.setId(resultSet.getInt("id_producto"));
                    producto.setNombre(resultSet.getString("nombre"));
                    producto.setCategoria(resultSet.getString("categoria"));
                    producto.setModelo(resultSet.getString("modelo"));
                    producto.setProveedor(resultSet.getString("proveedor"));
                    producto.setFechaRegistro(resultSet.getString("fecha_registro"));
                    producto.setCosto(resultSet.getDouble("costo"));
                    producto.setPrecio(resultSet.getDouble("precio"));
                    producto.setDescripcion(resultSet.getString("descripcion"));
                    producto.setStock(resultSet.getInt("stock"));
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener producto por ID");
            e.printStackTrace();
        }
        return producto;
    }
}
