package plugin.vertex101.easydetain.events;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import plugin.vertex101.easydetain.EasyDetain;

public class playerEvent implements Listener {

	@EventHandler
	public void onRename(InventoryClickEvent e) {

		if (e.getInventory() instanceof AnvilInventory)
			if (e.getInventory().contains(Material.SADDLE)) {
				NamespacedKey rez = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_rez");
				ItemStack itemStack = e.getCurrentItem();
				ItemMeta itemMeta = itemStack.getItemMeta();
				PersistentDataContainer container = itemMeta.getPersistentDataContainer();
				if (container.has(rez, PersistentDataType.STRING)) {
					if (e.getClickedInventory() == e.getInventory() && container.has(rez, PersistentDataType.STRING)
							&& e.getSlot() == 1)
						e.setCancelled(true);
				}
				if (e.getClickedInventory() == e.getInventory() && container.has(rez, PersistentDataType.STRING)
						&& e.getSlot() == 2)
					e.setCancelled(true);
			}

	}

	@EventHandler
	public void onRelease(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Action action = e.getAction();
		NamespacedKey type = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_type");
		NamespacedKey health = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_health");
		NamespacedKey prof = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_prof");
		NamespacedKey exp = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_exp");
		NamespacedKey level = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_level");
		NamespacedKey age = new NamespacedKey(EasyDetain.getInstance(), "easy_detain_age");
		ItemStack itemStack = player.getInventory().getItemInMainHand();
		ItemMeta itemMeta = itemStack.getItemMeta();

		if (itemMeta != null) {
			PersistentDataContainer container = itemMeta.getPersistentDataContainer();

			if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)
					&& player.getInventory().getItemInMainHand().getType().equals(Material.SADDLE)) {
				if (container.has(type, PersistentDataType.STRING)) {
					String getType = container.get(type, PersistentDataType.STRING);
					Double gethealth = container.get(health, PersistentDataType.DOUBLE);
					if (getType.equalsIgnoreCase("villager")) {
						String getProf = container.get(prof, PersistentDataType.STRING);
						Integer getlevel = container.get(level, PersistentDataType.INTEGER);
						Integer getage = container.get(age, PersistentDataType.INTEGER);
						int getexp = container.get(exp, PersistentDataType.INTEGER);

						if (action.equals(Action.RIGHT_CLICK_AIR)) {
							Villager villager = (Villager) player.getWorld().spawnEntity(player.getLocation(),
									EntityType.VILLAGER);
							villager.setVillagerExperience(getexp);
							villager.setVillagerLevel(getlevel);
							villager.setAge(getage);
							villager.setHealth(gethealth);
							villager.setProfession(Profession.valueOf(getProf));
						}
						if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
							Villager villager = (Villager) player.getWorld()
									.spawnEntity(e.getClickedBlock().getLocation().add(0, 1, 0), EntityType.VILLAGER);
							villager.setVillagerExperience(getexp);
							villager.setVillagerLevel(getlevel);
							villager.setAge(getage);
							villager.setHealth(gethealth);
							villager.setProfession(Profession.valueOf(getProf));
						}
					}
					if (!getType.equalsIgnoreCase("villager") && action.equals(Action.RIGHT_CLICK_AIR)) {
						EntityType mobtype = EntityType.valueOf(getType);
						LivingEntity mob = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), mobtype);
						mob.setHealth(gethealth);
					}
					if (!getType.equalsIgnoreCase("villager") && action.equals(Action.RIGHT_CLICK_BLOCK)) {
						EntityType mobtype = EntityType.valueOf(getType);
						LivingEntity mob = (LivingEntity) player.getWorld()
								.spawnEntity(e.getClickedBlock().getLocation().add(0, 1, 0), mobtype);
						mob.setHealth(gethealth);
					}
					player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
				} else {

				}

			}
		}
	}
}
