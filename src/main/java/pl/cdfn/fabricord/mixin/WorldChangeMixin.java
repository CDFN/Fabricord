package pl.cdfn.fabricord.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pl.cdfn.fabricord.callback.DimensionChangeCallback;

@Mixin(ServerPlayerEntity.class)
public class WorldChangeMixin {

  @Inject(method = "moveToWorld", at = @At("RETURN"))
  private void moveToWorld(ServerWorld serverWorld, CallbackInfoReturnable<Entity> callbackInfo) {
    DimensionChangeCallback.EVENT.invoker().changeDimension(serverWorld);
  }
}
