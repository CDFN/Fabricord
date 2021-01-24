package pl.cdfn.fabricord.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import pl.cdfn.fabricord.callback.DimensionChangeCallback;
import pl.cdfn.fabricord.callback.ServerDisconnectCallback;
import pl.cdfn.fabricord.callback.ServerJoinCallback;
import pl.cdfn.fabricord.discord.DiscordService;

@Environment(EnvType.CLIENT)
public class FabricordClient implements ClientModInitializer {

  private final DiscordService discordService = DiscordService.create();

  @Override
  public void onInitializeClient() {
    discordService.initialize();
    DimensionChangeCallback.EVENT.register(discordService::changeDimension);
    ServerJoinCallback.EVENT.register(discordService::joinServer);
    ServerDisconnectCallback.EVENT.register(discordService::disconnectServer);
    ClientLifecycleEvents.CLIENT_STARTED.register(discordService::enabled);
    ClientLifecycleEvents.CLIENT_STOPPING.register(discordService::shutdown);
  }
}
