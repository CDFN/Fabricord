package pl.cdfn.fabricord.discord;

import net.minecraft.client.MinecraftClient;
import net.minecraft.server.world.ServerWorld;
import pl.cdfn.fabricord.client.FabricordClient;

public interface DiscordService {

  /** Called when mod is being loaded ({@link FabricordClient#onInitializeClient()}) */
  void initialize();

  /**
   * Called when client got enabled and it's ready to pass info about it's state.
   *
   * @param client enabled client
   */
  void enabled(MinecraftClient client);

  /**
   * Called when player changes dimension.
   *
   * @param targetDimension dimension player went to
   */
  void changeDimension(ServerWorld targetDimension);

  /**
   * Called when player joins server
   *
   * @param localhost states whether connection is made to helps identifying singleplayer/multiplayer.
   * @param dimensionName dimension player is going to spawn in
   */
  void joinServer(boolean localhost, String dimensionName);

  /**
   * Called when player disconnects from server
   */
  void disconnectServer();

  /**
   * Called when client is shutting down.
   *
   * @param minecraftClient client which is being stopped
   */
  void shutdown(MinecraftClient minecraftClient);

  /**
   * Static method for getting default implementation of the service.
   *
   * @return new instance of default implementation of {@link DiscordService}
   * @see DiscordServiceImpl
   */
  static DiscordService create() {
    return new DiscordServiceImpl();
  }
}
