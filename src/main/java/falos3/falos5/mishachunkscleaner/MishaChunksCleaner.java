package falos3.falos5.mishachunkscleaner;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MishaChunksCleaner extends JavaPlugin {

    @Override
    public void onEnable() {
        optimizeChunks();
    }

    @Override
    public void onDisable() {
        optimizeChunks();
    }

    private void optimizeChunks() {
        File regionFolder = new File(getServer().getWorldContainer(), "world/region");

        if (!regionFolder.exists() || !regionFolder.isDirectory()) {
            return;
        }

        File[] regionFiles = regionFolder.listFiles();
        if (regionFiles == null || regionFiles.length == 0) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        long days = 3 * 86400000;

        for (File regionFile : regionFiles) {
            long lastModified = regionFile.lastModified();

            if (currentTime - lastModified >= days) {
                if (regionFile.delete()) {
                    getLogger().info("ОПТИМИЗИРОВАН ЧАНК: " + regionFile.getName());
                }
            }
        }
    }
}