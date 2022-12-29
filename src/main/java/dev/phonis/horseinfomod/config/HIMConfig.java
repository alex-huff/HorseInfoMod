package dev.phonis.horseinfomod.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import dev.phonis.horseinfomod.render.RGBAColor;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public
class HIMConfig
{

    public static final  String    configDirectory                      = "config/HorseInfoMod/";
    public static final  String    configFile                           = HIMConfig.configDirectory + "HIMConfig.json";
    private static final Gson      GSON                                 = new GsonBuilder().setPrettyPrinting()
        .create();
    public static final  int       defaultRenderScale                   = 50;
    public static        int       defaultOverlayForegroundTransparency = 255;
    public static        RGBAColor defaultPercentColor1                 = new RGBAColor(80, 65, 196,
        HIMConfig.defaultOverlayForegroundTransparency);
    public static        RGBAColor defaultPercentColor2                 = new RGBAColor(0, 0, 0,
        HIMConfig.defaultOverlayForegroundTransparency);
    public static        RGBAColor defaultTextColor                     = new RGBAColor(255, 255, 255,
        HIMConfig.defaultOverlayForegroundTransparency);
    public static        RGBAColor defaultOverlayBackgroundColor        = new RGBAColor(20, 20, 20, 200);
    public static        int       defaultMargin                        = 10;
    public static        boolean   defaultRoundNumbers                  = true;
    public static        int       defaultRoundingPrecision             = 4;
    public static final  HIMConfig INSTANCE                             = HIMConfig.load();

    public int       renderScale;
    public int       overlayForegroundTransparency;
    public RGBAColor percentColor1;
    public RGBAColor percentColor2;
    public RGBAColor overlayBackgroundColor;
    public RGBAColor textColor;
    public int       margin;
    public boolean   roundNumbers;
    public int       roundingPrecision;

    public
    HIMConfig()
    {
        this.renderScale                   = HIMConfig.defaultRenderScale;
        this.overlayForegroundTransparency = HIMConfig.defaultOverlayForegroundTransparency;
        this.percentColor1                 = HIMConfig.defaultPercentColor1;
        this.percentColor2                 = HIMConfig.defaultPercentColor2;
        this.overlayBackgroundColor        = HIMConfig.defaultOverlayBackgroundColor;
        this.textColor                     = HIMConfig.defaultTextColor;
        this.margin                        = HIMConfig.defaultMargin;
        this.roundNumbers                  = HIMConfig.defaultRoundNumbers;
        this.roundingPrecision             = HIMConfig.defaultRoundingPrecision;
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
    void setOverlayForegroundTransparency(int overlayForegroundTransparency)
    {
        this.overlayForegroundTransparency = overlayForegroundTransparency;
        this.percentColor1.a               = overlayForegroundTransparency;
        this.percentColor2.a               = overlayForegroundTransparency;
        this.textColor.a                   = overlayForegroundTransparency;
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
