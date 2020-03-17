package sk.gggedr.skyblockcore.Auth.MySql;

import org.bukkit.entity.Player;
import sk.gggedr.skyblockcore.Auth.DeCoding;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    public static Login instance;
    MySQLMain mysql = new MySQLMain();

    public boolean PasswordSucceful(Player p, String password){
        boolean s = false;
            try {
                PreparedStatement statement = mysql.getConnection()
                        .prepareStatement("SELECT * FROM " + mysql.table + " WHERE UUID=?");
                statement.setString(1, p.getUniqueId().toString());
                ResultSet results = statement.executeQuery();
                results.next();

                String psw = results.getString("PASSWORD");
                if(psw.equalsIgnoreCase(DeCoding.ToSHA256(password))){
                    s = true;
                } else {
                    s = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return s;
    }

}
