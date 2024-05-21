import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SkaneConnection {
    public static void main(String[] args) {
        Connection conexion = null;
        try {
            // Cargar el controlador JDBC de PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Configurar las propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("user", "tu_usuario");
            props.setProperty("password", "tu_contraseña");
            props.setProperty("ssl", "true");

            // URL de conexión a la base de datos
            String url = "jdbc:postgresql://localhost:5432/tu_basedatos";

            // Establecer la conexión
            conexion = DriverManager.getConnection(url, props);
            if (conexion != null) {
                System.out.println("¡Conexión exitosa a la base de datos PostgreSQL con SSL/TLS!");
            } else {
                System.out.println("¡No se pudo establecer la conexión a la base de datos!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el controlador JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
