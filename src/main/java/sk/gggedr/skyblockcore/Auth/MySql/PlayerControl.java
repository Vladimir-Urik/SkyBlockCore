package sk.gggedr.skyblockcore.Auth.MySql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerControl {

    public static PlayerControl instance;
    MySQLMain mysql = new MySQLMain();

    public boolean isRegister(UUID uuid) {
        try {
            PreparedStatement statement = mysql.getConnection()
                    .prepareStatement("SELECT * FROM " + mysql.table + " WHERE UUID=?");
            statement.setString(1, uuid.toString());

            ResultSet results = statement.executeQuery();
            if (results.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
