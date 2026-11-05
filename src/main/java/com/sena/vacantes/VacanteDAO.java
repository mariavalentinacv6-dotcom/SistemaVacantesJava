package com.sena.vacantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacanteDAO {

    public boolean registrar(Vacante vacante) throws SQLException {
        String sql = "INSERT INTO vacantes (titulo, empresa, ubicacion, salario, modalidad, descripcion) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setString(1, vacante.getTitulo());
            consulta.setString(2, vacante.getEmpresa());
            consulta.setString(3, vacante.getUbicacion());
            consulta.setDouble(4, vacante.getSalario());
            consulta.setString(5, vacante.getModalidad());
            consulta.setString(6, vacante.getDescripcion());

            return consulta.executeUpdate() > 0;
        }
    }

    public List<Vacante> listar() throws SQLException {
        List<Vacante> vacantes = new ArrayList<>();
        String sql = "SELECT * FROM vacantes ORDER BY id";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql);
             ResultSet resultado = consulta.executeQuery()) {

            while (resultado.next()) {
                Vacante vacante = new Vacante(
                    resultado.getInt("id"),
                    resultado.getString("titulo"),
                    resultado.getString("empresa"),
                    resultado.getString("ubicacion"),
                    resultado.getDouble("salario"),
                    resultado.getString("modalidad"),
                    resultado.getString("descripcion")
                );

                vacantes.add(vacante);
            }
        }

        return vacantes;
    }

    public Vacante buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM vacantes WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setInt(1, id);

            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    return new Vacante(
                        resultado.getInt("id"),
                        resultado.getString("titulo"),
                        resultado.getString("empresa"),
                        resultado.getString("ubicacion"),
                        resultado.getDouble("salario"),
                        resultado.getString("modalidad"),
                        resultado.getString("descripcion")
                    );
                }
            }
        }

        return null;
    }

    public boolean actualizar(Vacante vacante) throws SQLException {
        String sql = "UPDATE vacantes SET titulo = ?, empresa = ?, ubicacion = ?, salario = ?, modalidad = ?, descripcion = ? WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setString(1, vacante.getTitulo());
            consulta.setString(2, vacante.getEmpresa());
            consulta.setString(3, vacante.getUbicacion());
            consulta.setDouble(4, vacante.getSalario());
            consulta.setString(5, vacante.getModalidad());
            consulta.setString(6, vacante.getDescripcion());
            consulta.setInt(7, vacante.getId());

            return consulta.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM vacantes WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setInt(1, id);

            return consulta.executeUpdate() > 0;
        }
    }
}