package sk.gggedr.skyblockcore.Auth.MySql;

import org.bukkit.entity.Player;
import sk.gggedr.skyblockcore.Auth.DeCoding;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Register {
    public static Register instance;
    MySQLMain mysql = new MySQLMain();
    PlayerControl pc = new PlayerControl();


    public void newPlayer(final UUID uuid, Player player, String password) {
        try {
            PreparedStatement statement = mysql.getConnection()
                    .prepareStatement("SELECT * FROM " + mysql.table + " WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            System.out.print(1);
            if (pc.isRegister(uuid) != true) {
                PreparedStatement insert = mysql.getConnection()
                        .prepareStatement("INSERT INTO " + mysql.table + " (UUID,NICK,IP,PASSWORD) VALUES (?,?,?,?,?)");
                insert.setString(1, uuid.toString());
                insert.setString(2, player.getName());
                insert.setString(3, player.getAddress().toString());
                insert.setString(4, DeCoding.ToSHA256(password));
                insert.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
