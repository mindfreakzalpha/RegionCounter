package com.example.regioncounter;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.RegionContainer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RegionCounterExpansion extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "regioncounter";
    }

    @Override
    public @NotNull String getAuthor() {
        return "ChatGPT";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier) {
        if (identifier.startsWith("players_")) {
            String regionName = identifier.substring("players_".length());

            if (player != null) {
                World world = player.getWorld();
                RegionContainer container = WorldGuardPlugin.inst().getRegionContainer();
                RegionManager regions = container.get(BukkitAdapter.adapt(world));
                if (regions == null) return "0";

                int count = 0;
                for (Player p : world.getPlayers()) {
                    ApplicableRegionSet set = regions.getApplicableRegions(BukkitAdapter.asBlockVector(p.getLocation()));
                    for (ProtectedRegion region : set) {
                        if (region.getId().equalsIgnoreCase(regionName)) {
                            count++;
                            break;
                        }
                    }
                }
                return String.valueOf(count);
            }
        }
        return null;
    }
}
