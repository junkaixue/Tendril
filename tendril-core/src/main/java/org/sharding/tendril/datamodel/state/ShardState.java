package org.sharding.tendril.datamodel.state;

import java.util.List;

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

  /**
   * Convert a string to a ShardState.
   *
   * @param state The string representation of the state.
   * @return The ShardState.
   */
  public static ShardState fromString(String state) {
    switch (state) {
      case "LEADER":
        return LEADER;
      case "FOLLOWER":
        return FOLLOWER;
      case "OFFLINE":
        return OFFLINE;
      case "ERROR":
        return ERROR;
      default:
        throw new IllegalArgumentException("Unknown state: " + state);
    }
  }

  /**
   * Get the list of states in priority order.
   *
   * @return The list of states in priority order.
   */
  public static List<ShardState> getStatePriorityList() {
    return List.of(LEADER, FOLLOWER, OFFLINE, ERROR);
  }
}
