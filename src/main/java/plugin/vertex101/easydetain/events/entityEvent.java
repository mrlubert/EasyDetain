package plugin.vertex101.easydetain.events;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import plugin.vertex101.easydetain.EasyDetain;

@SuppressWarnings("unused")
public class entityEvent implements Listener {

	@EventHandler
	public void onCapture(PlayerInteractEntityEvent e) {
		Entity entity = e.getRightClicked();
		Player player = e.getPlayer();

		if (player.isSneaking()) {
			if (entity instanceof LivingEntity) {
				NamespacedKey type = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_type");
				NamespacedKey health = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_health");
				NamespacedKey rez = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_rez");
				NamespacedKey prof = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_prof");
				NamespacedKey exp = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_exp");
				NamespacedKey level = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_level");
				NamespacedKey age = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_age");
				ItemStack itemStack = new ItemStack(Material.SADDLE);
				ItemStack air = new ItemStack(Material.AIR);
				ItemMeta itemMeta = itemStack.getItemMeta();
				PersistentDataContainer getData = itemMeta.getPersistentDataContainer();
				if (entity.getType() == EntityType.VILLAGER) {
					Villager villager = (Villager) entity;
					itemMeta.setDisplayName(entity.getName());
					getData.set(health, PersistentDataType.DOUBLE, villager.getHealth());
					getData.set(type, PersistentDataType.STRING, entity.getType().toString());
					getData.set(age, PersistentDataType.INTEGER, villager.getAge());
					getData.set(prof, PersistentDataType.STRING, villager.getProfession().toString());
					getData.set(rez, PersistentDataType.STRING, "rez");
					getData.set(exp, PersistentDataType.INTEGER, villager.getVillagerExperience());
					getData.set(level, PersistentDataType.INTEGER, villager.getVillagerLevel());

				} else {
					itemMeta.setDisplayName(entity.getName());
					getData.set(type, PersistentDataType.STRING, entity.getType().toString());
					getData.set(health, PersistentDataType.DOUBLE, ((LivingEntity) entity).getHealth());
					getData.set(rez, PersistentDataType.STRING, "rez");
				}

				itemStack.setItemMeta(itemMeta);
				player.getInventory().addItem(itemStack);
				entity.remove();
			}
		}
	}
}
