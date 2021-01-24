package pl.cdfn.fabricord.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.s2c.play.DisconnectS2CPacket;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.cdfn.fabricord.callback.ServerDisconnectCallback;
import pl.cdfn.fabricord.callback.ServerJoinCallback;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ServerConnectionMixin {

  @Shadow
  public abstract ClientConnection getConnection();

  @Inject(method = "onGameJoin", at = @At("RETURN"))
  private void onGameJoin(GameJoinS2CPacket packet, CallbackInfo callbackInfo) {
    ServerJoinCallback.EVENT.invoker().serverJoin(this.getConnection().isLocal(), packet.getDimensionId().getValue().getPath());
  }

  @Inject(method = "onDisconnect", at = @At("RETURN"))
  private void onDisconnect(DisconnectS2CPacket packet, CallbackInfo callbackInfo) {
    ServerDisconnectCallback.EVENT.invoker().serverDisconnect();
  }
}
