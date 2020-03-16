package sk.gggedr.skyblockcore.Auth.MySql;

import org.bukkit.entity.Player;
import sk.gggedr.skyblockcore.Auth.DeCoding;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    public static Login instance;

    public boolean PasswordSucceful(Player p, String password){
        boolean s = false;
            try {
                PreparedStatement statement = MySQLMain.getInstance().getConnection()
                        .prepareStatement("SELECT * FROM " + MySQLMain.getInstance().table + " WHERE UUID=?");
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

    public static Login getInstance(){
        return instance;
    }
}
