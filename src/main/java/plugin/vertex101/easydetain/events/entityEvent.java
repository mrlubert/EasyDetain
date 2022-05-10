package plugin.vertex101.easydetain.events;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import plugin.vertex101.easydetain.EasyDetain;

public class entityEvent implements Listener {

    @EventHandler
    public void onCapture(PlayerInteractEntityEvent e) {
        Entity entity = e.getRightClicked();
        Player player = e.getPlayer();

        if(player.isSneaking()) {
            if(entity instanceof LivingEntity) {
                NamespacedKey type = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_type");
                NamespacedKey rez = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_rez");
                NamespacedKey prof = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_prof");
                ItemStack itemStack = new ItemStack(Material.TOTEM_OF_UNDYING);
                ItemMeta itemMeta = itemStack.getItemMeta();

                if(entity.getType() == EntityType.VILLAGER) {
                    Villager villager = (Villager) entity;
                    itemMeta.setDisplayName(entity.getName());
                            itemMeta.getPersistentDataContainer().set(type, PersistentDataType.STRING, entity.getType().toString());
                    itemMeta.getPersistentDataContainer().set(prof, PersistentDataType.STRING, villager.getProfession().toString());
                    itemMeta.getPersistentDataContainer().set(rez, PersistentDataType.STRING, "rez");
                } else {
                    itemMeta.setDisplayName(entity.getName());
                    itemMeta.getPersistentDataContainer().set(type, PersistentDataType.STRING, entity.getType().toString());
                    itemMeta.getPersistentDataContainer().set(rez, PersistentDataType.STRING, "rez");
                }

                itemStack.setItemMeta(itemMeta);
                player.getInventory().addItem(itemStack);
                entity.remove();
            }
        }
    }
}
