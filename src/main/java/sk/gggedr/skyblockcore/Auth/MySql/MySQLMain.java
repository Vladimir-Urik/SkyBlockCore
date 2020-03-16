package sk.gggedr.skyblockcore.Auth.MySql;

import sk.gggedr.skyblockcore.SkyBlockCore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLMain {
    private Connection connection;
    public String host, database, username, password, table;
    public int port;
    public static MySQLMain instance;


    public void mysqlSetup() {
        host = SkyBlockCore.getInstance().getConfig().getString("ip");
        port = SkyBlockCore.getInstance().getConfig().getInt("port");
        database = SkyBlockCore.getInstance().getConfig().getString("database");
        username = SkyBlockCore.getInstance().getConfig().getString("username");
        password = SkyBlockCore.getInstance().getConfig().getString("password");
        table = "auth";

        try {

            synchronized (SkyBlockCore.getInstance()) {
                if (getConnection() != null && !getConnection().isClosed()) {
                    return;
                }

                Class.forName("com.mysql.jdbc.Driver");
                setConnection(
                        DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database,
                                this.username, this.password));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static MySQLMain getInstance(){
        return instance;
    }


}
