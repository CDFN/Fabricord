package pl.cdfn.fabricord.discord.key;

import java.util.Collection;
import java.util.HashSet;

public class DimensionAssetKey extends AssetKey {
  public static final Collection<DimensionAssetKey> DIMENSION_ASSET_KEYS = new HashSet<>();

  public static final DimensionAssetKey OVERWORLD = new DimensionAssetKey("overworld");
  public static final DimensionAssetKey THE_NETHER = new DimensionAssetKey("the_nether");
  public static final DimensionAssetKey THE_END = new DimensionAssetKey("the_end");

  static {
    DIMENSION_ASSET_KEYS.add(OVERWORLD);
    DIMENSION_ASSET_KEYS.add(THE_NETHER);
    DIMENSION_ASSET_KEYS.add(THE_END);
  }

  private static final String PREFIX = "dimension_";

  public DimensionAssetKey(String key) {
    super(key);
  }

  public String getKeyWithPrefix() {
    return PREFIX + this.getKey();
  }
}
