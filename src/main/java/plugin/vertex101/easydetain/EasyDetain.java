package plugin.vertex101.easydetain;

import org.bukkit.plugin.java.JavaPlugin;
import plugin.vertex101.easydetain.events.entityEvent;
import plugin.vertex101.easydetain.events.playerEvent;

public final class EasyDetain extends JavaPlugin {

	private static EasyDetain instance;

	@Override
	public void onEnable() {
		instance = this;

		getServer().getPluginManager().registerEvents(new entityEvent(), this);
		getServer().getPluginManager().registerEvents(new playerEvent(), this);
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

	public static EasyDetain getInstance() {
		return instance;
	}
}
