package plugin.vertex101.easydetain.events;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import plugin.vertex101.easydetain.EasyDetain;

public class playerEvent implements Listener {

    @EventHandler
    public void onRelease(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        NamespacedKey type = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_type");
        NamespacedKey prof = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_prof");
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        if(action.equals(Action.RIGHT_CLICK_AIR) && player.getInventory().getItemInMainHand().getType().equals(Material.TOTEM_OF_UNDYING)) {
            if(container.has(type, PersistentDataType.STRING)) {
                String getType = container.get(type, PersistentDataType.STRING);
                if(getType.equalsIgnoreCase("villager")) {
                    String getProf = container.get(prof, PersistentDataType.STRING);

                    Villager villager = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
                    villager.setProfession(Profession.valueOf(getProf));

                    player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                } else {

                }

            }
        }
    }
}
