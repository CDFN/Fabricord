package pl.cdfn.fabricord.discord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.world.ServerWorld;
import org.apache.logging.log4j.Level;
import pl.cdfn.fabricord.Fabricord;
import pl.cdfn.fabricord.discord.key.AssetKey;
import pl.cdfn.fabricord.discord.key.DimensionAssetKey;
import pl.cdfn.fabricord.discord.rpc.RichPresenceRenderer;

public class DiscordServiceImpl implements DiscordService {
  private static final DiscordEventHandlers HANDLERS = new DiscordEventHandlers.Builder()
          .setReadyEventHandler(user -> Fabricord.LOGGER.info("Discord RPC is ready: {} {}", user.username, user.discriminator))
          .setDisconnectedEventHandler((errorCode, message) -> Fabricord.LOGGER.warn("Discord RPC has disconnected: {} {}", errorCode, message))
          .build();

  private boolean isLocalhost = false;

  @Override
  public void initialize() {
    Fabricord.LOGGER.info("Discord RPC initialization started");
    DiscordRPC.discordInitialize("802579600856055878", HANDLERS, true);
    DiscordRPC.discordUpdatePresence(RichPresenceRenderer.renderLoading());
  }

  @Override
  public void enabled(MinecraftClient client) {
    Fabricord.LOGGER.info("Discord RPC started successfully");
    DiscordRPC.discordUpdatePresence(RichPresenceRenderer.renderIdle());
  }

  @Override
  public void changeDimension(ServerWorld targetDimension) {
    DimensionAssetKey assetKey = getAssetKeyFromDimensionName(targetDimension.getRegistryKey().getValue().getPath());
    DiscordRPC.discordUpdatePresence(RichPresenceRenderer.renderChangeDimension(assetKey, this.isLocalhost));
  }

  @Override
  public void joinServer(boolean localhost, String dimensionName) {
    this.isLocalhost = localhost;
    DimensionAssetKey assetKey = getAssetKeyFromDimensionName(dimensionName);
    DiscordRPC.discordUpdatePresence(RichPresenceRenderer.renderChangeDimension(assetKey, this.isLocalhost));
  }

  @Override
  public void disconnectServer() {
    DiscordRPC.discordUpdatePresence(RichPresenceRenderer.renderIdle());
  }

  @Override
  public void shutdown(MinecraftClient minecraftClient) {
    DiscordRPC.discordShutdown();
  }

  private DimensionAssetKey getAssetKeyFromDimensionName(String dimensionName) {
    return DimensionAssetKey.DIMENSION_ASSET_KEYS
            .stream()
            .filter(entry -> entry.getKey().equalsIgnoreCase(dimensionName))
            .findFirst()
            .orElse(new DimensionAssetKey(AssetKey.MISSING.getKey()));
  }
}
