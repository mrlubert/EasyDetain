package plugin.vertex101.easydetain.events;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import plugin.vertex101.easydetain.EasyDetain;

public class deathEvent implements Listener {

    @EventHandler
    public void onRez(EntityResurrectEvent e) {
        NamespacedKey rez = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_rez");
        Player player = (Player) e.getEntity();
        ItemStack itemStack = player.getInventory().getItemInOffHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        if(player.getInventory().getItemInOffHand().getType().equals(Material.TOTEM_OF_UNDYING)) {
            if(container.has(rez, PersistentDataType.STRING)) {
                e.setCancelled(true);
            }
        }
    }
}
