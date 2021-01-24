package pl.cdfn.fabricord.discord.rpc;

import net.arikia.dev.drpc.DiscordRichPresence;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.lang3.text.WordUtils;
import pl.cdfn.fabricord.discord.key.AssetKey;
import pl.cdfn.fabricord.discord.key.DimensionAssetKey;

public class RichPresenceRenderer {
  public static DiscordRichPresence renderLoading() {
    return new DiscordRichPresence.Builder("Loading...")
            .setBigImage(AssetKey.LOGO.getKey(), "")
            .build();
  }

  public static DiscordRichPresence renderIdle() {
    return new DiscordRichPresence.Builder("Looking at menu")
            .setDetails(String.format("Playing with %d mods", FabricLoader.getInstance().getAllMods().size()))
            .setStartTimestamps(System.currentTimeMillis())
            .setBigImage(AssetKey.LOGO.getKey(), "Idle")
            .build();
  }

  public static DiscordRichPresence renderChangeDimension(DimensionAssetKey dimensionAssetKey, boolean localhost) {
    return new DiscordRichPresence.Builder(localhost ? "Singleplayer" : "Multiplayer")
            .setDetails(String.format("Playing with %d mods", FabricLoader.getInstance().getAllMods().size()))
            .setStartTimestamps(System.currentTimeMillis())
            .setBigImage(dimensionAssetKey.getKeyWithPrefix(), beautifyAssetKey(dimensionAssetKey))
            .build();
  }

  private static String beautifyAssetKey(AssetKey assetKey) {
    return WordUtils.capitalize(assetKey.getKey().replace("_", " "));
  }
}
