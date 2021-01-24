package pl.cdfn.fabricord.callback;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface ServerJoinCallback {

  Event<ServerJoinCallback> EVENT = EventFactory.createArrayBacked(ServerJoinCallback.class, handlers -> (localhost, dimensionName) -> {
    for (ServerJoinCallback handler : handlers) {
      handler.serverJoin(localhost, dimensionName);
    }
  });

  void serverJoin(boolean localhost, String dimensionName);
}
