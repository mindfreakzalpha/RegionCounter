package com.example.regioncounter;

import org.bukkit.plugin.java.JavaPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class RegionCounterPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new RegionCounterExpansion().register();
            getLogger().info("RegionCounter placeholder registered.");
        } else {
            getLogger().warning("PlaceholderAPI not found. Placeholder not registered.");
        }
    }
}
