package pl.cdfn.fabricord.callback;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface ServerDisconnectCallback {

  Event<ServerDisconnectCallback> EVENT = EventFactory.createArrayBacked(ServerDisconnectCallback.class, handlers -> () -> {
    for (ServerDisconnectCallback handler : handlers) {
      handler.serverDisconnect();
    }
  });

  void serverDisconnect();
}