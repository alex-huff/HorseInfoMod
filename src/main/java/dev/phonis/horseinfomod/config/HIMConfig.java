package dev.phonis.horseinfomod.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public
class HIMConfig
{

    public static final  String    configDirectory    = "config/HorseInfoMod/";
    public static final  String    configFile         = HIMConfig.configDirectory + "HIMConfig.json";
    private static final Gson      GSON               = new GsonBuilder().setPrettyPrinting().create();
    public static final  HIMConfig INSTANCE           = HIMConfig.load();
    public static final  int       defaultRenderScale = 50;

    public int renderScale;
    public
    HIMConfig()
    {
        this.renderScale = HIMConfig.defaultRenderScale;
    }

    private static
    HIMConfig load()
    {
        if (Files.exists(Path.of(HIMConfig.configFile)))
        {
            try (FileReader reader = new FileReader(HIMConfig.configFile))
            {
                return GSON.fromJson(reader, HIMConfig.class);
            }
            catch (IOException | JsonSyntaxException e)
            {
                System.out.println("Could not read config. Using defaults.");
            }
        }

        return new HIMConfig();
    }

    public
    void saveToFile() throws IOException
    {
        Path path   = Path.of(HIMConfig.configFile);
        Path parent = path.getParent();

        if (!Files.exists(parent))
        {
            Files.createDirectories(parent);
        }

        // Atomic file replace
        Path tempPath = path.resolveSibling(path.getFileName() + ".tmp");

        Files.writeString(tempPath, GSON.toJson(this));
        Files.move(tempPath, path, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
    }

    public static
    void trySave()
    {
        try
        {
            HIMConfig.INSTANCE.saveToFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
