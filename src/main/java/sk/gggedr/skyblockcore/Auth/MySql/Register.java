package sk.gggedr.skyblockcore.Auth.MySql;

import org.bukkit.entity.Player;
import sk.gggedr.skyblockcore.Auth.DeCoding;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Register {
    public static Register instance;


    public void newPlayer(final UUID uuid, Player player, String password) {
        try {
            PreparedStatement statement = MySQLMain.getInstance().getConnection()
                    .prepareStatement("SELECT * FROM " + MySQLMain.getInstance().table + " WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            System.out.print(1);
            if (PlayerControl.getInstance().isRegister(uuid) != true) {
                PreparedStatement insert = MySQLMain.getInstance().getConnection()
                        .prepareStatement("INSERT INTO " + MySQLMain.getInstance().table + " (UUID,NICK,IP,PASSWORD) VALUES (?,?,?,?,?)");
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

    public static Register getInstance(){
        return instance;
    }
}
