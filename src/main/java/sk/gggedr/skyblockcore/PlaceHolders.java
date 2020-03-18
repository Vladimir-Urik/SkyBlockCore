package sk.gggedr.skyblockcore;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class PlaceHolders {

    public class PlaceholderRegistry {
        private Map<String, Placeholder> placeholders = new HashMap<>();

        public PlaceholderRegistry() {
            placeholders.put("myPlaceholder", new Placeholder() {
                public String invoke(Player player) {
                    return "yeeeet";
                }
            });
        }

        public String usePlaceholders(String message, Player player) {
            StringBuffer sb = new StringBuffer(message);

            placeholders.forEach((placeholder, executor) -> {
                //sb = message.replaceAll("(?i)<" + placeholder + ">", executor.invoke(player));
            });
            return message;
        }

    }



    public abstract class Placeholder {

        public abstract String invoke(Player player);

    }
}

