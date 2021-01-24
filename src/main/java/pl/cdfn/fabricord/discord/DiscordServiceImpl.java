package pl.cdfn.fabricord.discord;

import java.util.Arrays;
import java.util.logging.Level;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.world.ServerWorld;
import org.apache.commons.lang3.text.WordUtils;
import pl.cdfn.fabricord.Fabricord;
import pl.cdfn.fabricord.discord.key.AssetKey;
import pl.cdfn.fabricord.discord.key.DimensionAssetKey;

public class DiscordServiceImpl implements DiscordService {
  private static final DiscordEventHandlers HANDLERS =
      new DiscordEventHandlers.Builder()
          .setReadyEventHandler(user -> Fabricord.LOGGER.log(Level.INFO, "Discord RPC is ready: {0} {1}", new Object[] {user.username, user.discriminator}))
          .setDisconnectedEventHandler((errorCode, message) -> Fabricord.LOGGER.log(Level.WARNING, "Discord RPC has disconnected: {0} {1}", new Object[] {errorCode, message}))
          .build();
  private final DiscordRichPresence richPresence = new DiscordRichPresence();

  private boolean isConnected = false;
  private boolean isLocalhost = false;

  @Override
  public void initialize() {
    DiscordRPC.discordInitialize("802579600856055878", HANDLERS, true);
    richPresence.largeImageKey = AssetKey.LOGO.getKey();
    richPresence.details = "Loading...";
    richPresence.startTimestamp = System.currentTimeMillis();
    DiscordRPC.discordUpdatePresence(richPresence);
  }

  @Override
  public void enabled(MinecraftClient client) {
    final int modsCount = FabricLoader.getInstance().getAllMods().size();

    richPresence.details = String.format("Playing with %s mods", modsCount);
    DiscordRPC.discordUpdatePresence(richPresence);
  }

  @Override
  public void changeDimension(ServerWorld targetDimension) {
    AssetKey dimensionType = DimensionAssetKey.DIMENSION_ASSET_KEYS
            .stream()
            .filter(entry -> entry.getKey().equalsIgnoreCase(targetDimension.getRegistryKey().getValue().getPath()))
            .findFirst()
            .orElse(new DimensionAssetKey(AssetKey.MISSING.getKey()));
    richPresence.largeImageKey = dimensionType.getKey();
    // THE_NETHER -> The Nether
    richPresence.largeImageText = WordUtils.capitalize(dimensionType.getKey().replace("_", " "));
    DiscordRPC.discordUpdatePresence(richPresence);
  }

  @Override
  public void shutdown(MinecraftClient minecraftClient) {
    DiscordRPC.discordShutdown();
  }

  public boolean isConnected() {
    return isConnected;
  }

  public void setConnected(boolean connected) {
    isConnected = connected;
  }

  public boolean isLocalhost() {
    return isLocalhost;
  }

  public void setLocalhost(boolean localhost) {
    isLocalhost = localhost;
  }
}
