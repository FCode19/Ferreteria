package modelo.dao;


public class PruebaConexionBD {

    public static void main(String[] args) {
        if (Conexion.getConexion() != null) {
            System.out.println("✅ Conexión exitosa a la BD");
        } else {
            System.out.println("❌ Error de conexión");
        }
    }
}
