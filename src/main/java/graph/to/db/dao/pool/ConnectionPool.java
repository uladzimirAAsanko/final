package graph.to.db.dao.pool;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;


public class ConnectionPool{
    private static final String FILE_DATABASE_CONFIG = "config/database";
    private static final String URL = "url";
    private static final String POOL_SIZE = "poolSize";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String TIMEZONE = "serverTimezone";
    private static final String ENCODING = "encoding";
    private static final String UNICODE = "useUnicode";
    private int poolSize;
    private static ConnectionPool instance = new ConnectionPool();
    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenConnections;

    public static ConnectionPool getINSTANCE() {
        return instance;
    }

    private ConnectionPool(){
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(FILE_DATABASE_CONFIG);
            String url = resourceBundle.getString(URL);
            String user = resourceBundle.getString(USER);
            String password = resourceBundle.getString(PASSWORD);
            String serverTimezone = resourceBundle.getString(TIMEZONE);
            String encoding = resourceBundle.getString(ENCODING);
            String useUnicode = resourceBundle.getString(UNICODE);
            freeConnections = new LinkedBlockingDeque<>();
            givenConnections = new ArrayDeque<>();
            Class.forName("org.postgresql.Driver");
            Properties properties = new Properties();
            properties.put(USER, user);
            properties.put(PASSWORD, password);
            properties.put(TIMEZONE, serverTimezone);
            properties.put(ENCODING, encoding);
            properties.put(UNICODE, useUnicode);
            Integer poolSize = Integer.parseInt(resourceBundle.getString(POOL_SIZE));
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, properties);
                freeConnections.offer(new ProxyConnection(connection));
            }
        }catch (SQLException | ClassNotFoundException ex){
            throw new RuntimeException("Database connection wasn't established", ex);
        }
    }

    public Connection getConnection(){
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenConnections.offer(connection);
        } catch (InterruptedException e) {
        }
        return connection;
    }


    public void releaseConnection(ProxyConnection proxyConnection) {
        if (proxyConnection instanceof ProxyConnection && givenConnections.remove(proxyConnection)) {
            freeConnections.offer((ProxyConnection) proxyConnection);
        } else {
        }
    }

    public void destroyPool() {
        try {
            for (int i = 0; i < poolSize; i++) {
                freeConnections.take().reallyClose();
            }
        } catch (InterruptedException e) {
        } finally {
            while (DriverManager.getDrivers().hasMoreElements()) {
                try {
                    DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
                } catch (SQLException e) {
                }
            }
        }
    }
}