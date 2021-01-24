package pl.cdfn.fabricord.callback;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.world.ServerWorld;

/**
 * Called whenever player changes dimension (e.g uses portal)
 *
 * @author CDFN
 */
public interface DimensionChangeCallback {

  Event<DimensionChangeCallback> EVENT = EventFactory.createArrayBacked(DimensionChangeCallback.class, handlers -> dimension -> {
    for (DimensionChangeCallback callback : handlers) {
      callback.changeDimension(dimension);
    }
  });

  void changeDimension(ServerWorld target);
}
