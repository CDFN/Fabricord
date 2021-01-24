package pl.cdfn.fabricord.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.cdfn.fabricord.callback.ServerDisconnectCallback;

@Mixin(MinecraftClient.class)
public class ServerDisconnectMixin {
  @Inject(method = "disconnect(Lnet/minecraft/client/gui/screen/Screen;)V", at = @At("RETURN"))
  public void disconnect(Screen screen, CallbackInfo callbackInfo) {
    ServerDisconnectCallback.EVENT.invoker().serverDisconnect();
  }
}
