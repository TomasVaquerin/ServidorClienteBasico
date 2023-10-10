package org.example.servives;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

// import build.grandle.kts

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.h2.tools.RunScript;


public class DatabaseManager implements AutoCloseable {
    private static DatabaseManager instance;
    private Connection connection;
    private final boolean startTables = false;

    private final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);
    String serverUrl;
    String serverPort;
    String dataBaseName;
    private String user;
    private String password;
    private String initScript;
    private String jdbcDriver;
    private String connectionUrl;
    /**
     * Método para obtener la instancia de la base de datos
     * Singleton
     * @return
     */
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    /**
     * Constructor privado
     */

    private DatabaseManager() {
        try {
            initConfig();
            openConnection();
            if (startTables) {
                createTable();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initConfig() {
        // Leemos el fichero de configuración
        var propsFile = ClassLoader.getSystemResource("database.properties").getFile();
        var props = new Properties();
        try {
            props.load(new FileInputStream(propsFile));
            // Comentar o ajustar segun el tipo de base de datos y propiedades que se quieran usar
            serverUrl = props.getProperty("database.url", "localhost");
            serverPort = props.getProperty("database.port", "3306");
            dataBaseName = props.getProperty("database.name", "AppDatabase");
            jdbcDriver = props.getProperty("database.driver", "org.h2.Driver");
            user = props.getProperty("database.user", "sa");
            password = props.getProperty("database.password", "");

            connectionUrl = props.getProperty("database.connectionUrl", "jdbc:h2:mem:" + dataBaseName + ";DB_CLOSE_DELAY=-1");
            initScript = props.getProperty("database.initScript", ClassLoader.getSystemResource("init.sql").getFile());

            logger.debug("Configuración de acceso a la Base de Datos cargada");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void openConnection() throws SQLException {
        connection = DriverManager.getConnection(serverUrl, user, password);
    }

    private void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void createTable() {
        try (Statement stmt = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Temperaturas (id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(255))";
            stmt.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(String nombre) {
        try (Statement statement = connection.createStatement()) {
            String insertSQL = "INSERT INTO Temperaturas (nombre) VALUES ('" + nombre + "')";
            statement.executeUpdate(insertSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeScript(String scriptFileName) {
        try (FileReader reader = new FileReader(scriptFileName)) {
            RunScript.execute(connection, reader);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Método para cerrar la conexión
     * Necesario para el AutoCloseable
     * @throws Exception
     */

    @Override
    public void close() throws Exception {
        closeConnection();
    }
}