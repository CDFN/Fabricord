package pl.cdfn.fabricord.discord;

/**
 * Determines asset keys defined in application in https://discord.com/developers/ Non-default
 * applications should follow names defined in these classes.
 */
public enum AssetKey {
  LOGO("logo"),
  MISSING("missing");

  private final String key;

  AssetKey(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  public enum Dimension {
    UNKNOWN(MISSING.getKey(), false),
    OVERWORLD("overworld"),
    THE_NETHER("the_nether"),
    THE_END("the_end");

    private static final String PREFIX = "dimension_";
    private final String key;

    Dimension(String key) {
      this.key = PREFIX + key;
    }

    Dimension(String key, boolean prefix) {
      if (!prefix) {
        this.key = key;
        return;
      }
      this.key = PREFIX + key;
    }

    public String getKey() {
      return key;
    }
  }
}
