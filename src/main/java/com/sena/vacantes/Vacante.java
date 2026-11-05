
package com.sena.vacantes;

public class Vacante {

    private int id;
    private String titulo;
    private String empresa;
    private String ubicacion;
    private double salario;
    private String modalidad;
    private String descripcion;

    public Vacante(int id, String titulo, String empresa,
                   String ubicacion, double salario,
                   String modalidad, String descripcion) {

        this.id = id;
        this.titulo = titulo;
        this.empresa = empresa;
        this.ubicacion = ubicacion;
        this.salario = salario;
        this.modalidad = modalidad;
        this.descripcion = descripcion;
    }

    public Vacante(String titulo, String empresa,
                   String ubicacion, double salario,
                   String modalidad, String descripcion) {

        this(0, titulo, empresa, ubicacion,
             salario, modalidad, descripcion);
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public double getSalario() {
        return salario;
    }

    public String getModalidad() {
        return modalidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "\nTítulo: " + titulo +
                "\nEmpresa: " + empresa +
                "\nUbicación: " + ubicacion +
                "\nSalario: $" + salario +
                "\nModalidad: " + modalidad +
                "\nDescripción: " + descripcion +
                "\n--------------------------------";
    }
}
