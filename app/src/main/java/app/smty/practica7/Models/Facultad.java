package app.smty.practica7.Models;

public class Facultad {
    private String facultad, decano, direccion, image, telefono, email;
    private double[] map;

    public Facultad() {
    }

    public Facultad(String facultad, String decano, String direccion, String image,
                    String telefono, String email, double[] map) {
        this.facultad = facultad;
        this.decano = decano;
        this.direccion = direccion;
        this.image = image;
        this.telefono = telefono;
        this.email = email;
        this.map = map;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getDecano() {
        return decano;
    }

    public void setDecano(String decano) {
        this.decano = decano;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double[] getMap() {
        return map;
    }

    public void setMap(double[] map) {
        this.map = map;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%1$s,%2$s,%3$s,%4$s,%5$s,%6$s",
                facultad, decano, direccion, image, telefono, email);
    }
}
