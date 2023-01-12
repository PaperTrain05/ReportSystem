package io.paper.report;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main plugin;

    public Main() {
        plugin = this;
    }
    public static Main getInstance() {
        return plugin;
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("report").setExecutor(new ReportCommand());
    }
}
