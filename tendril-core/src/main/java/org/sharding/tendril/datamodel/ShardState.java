package org.sharding.tendril.datamodel;

/**
 * List of a shard's state based with priority. The smaller of priority number, the higher of the
 * state.
 * <p>
 * ERROR is a special state that indicates the shard is in an error state.
 */
public enum ShardState {
  LEADER(0),
  FOLLOWER(1),
  OFFLINE(2),
  ERROR(-1);

  private final int _priority;

  ShardState(int priority) {
    _priority = priority;
  }

  public int getPriority() {
    return _priority;
  }
}
