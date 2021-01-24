package pl.cdfn.fabricord.discord.key;

import java.util.Collection;

public class AssetKey {
  public static final AssetKey LOGO = new AssetKey("logo");
  public static final AssetKey MISSING = new AssetKey("missing");

  private final String key;

  public AssetKey(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
