package org.sharding.tendril.controller.planner;

import com.google.gson.annotations.Expose;
import java.util.Collections;
import java.util.Map;
import org.sharding.tendril.datamodel.state.ShardState;


/**
 * PlannedActions is a class that represents the planned actions for a specific resource in a
 * distributed system. It contains a map of instances and their corresponding next states.
 */
public class PlannedActions {

  @Expose
  private final String _resourceName;

  @Expose
  private Map<String, Map<String, ShardState>> _nextStateMap;

  public PlannedActions(String resourceName) {
    _resourceName = resourceName;
    _nextStateMap = Collections.emptyMap();
  }

  /**
   * Get the resource name.
   *
   * @return the resource name.
   */
  public String getResourceName() {
    return _resourceName;
  }

  /**
   * Update the next state for a specific instance.
   *
   * @param instance  the instance name.
   * @param nextState the next state for the instance.
   */
  public synchronized void updateNextState(String shardId, String instance, ShardState nextState) {
    if (!_nextStateMap.containsKey(shardId)) {
      _nextStateMap.put(shardId, Collections.emptyMap());
    }
    _nextStateMap.get(shardId).put(instance, nextState);
  }

  /**
   * Update the next state with instance for a specific shard.
   *
   * @param shardId      the shard name.
   * @param nextStateMap the next state map for the shard.
   */
  public synchronized void updateNextStates(String shardId, Map<String, ShardState> nextStateMap) {
    _nextStateMap.put(shardId, nextStateMap);
  }

  /**
   * Remove the next state for a specific shard.
   *
   * @param shardId the shard name.
   */
  public synchronized void removeShard(String shardId) {
    _nextStateMap.remove(shardId);
  }

  /**
   * Remove the next state for a specific instance.
   *
   * @param shardId  the shard name.
   * @param instance the instance name.
   */
  public synchronized void removeNextState(String shardId, String instance) {
    if (_nextStateMap.containsKey(shardId)) {
      _nextStateMap.get(shardId).remove(instance);
    }
  }

  /**
   * Get the next state for without modifying the map.
   *
   * @return the next state map.
   */
  public Map<String, Map<String, ShardState>> getNextStateMap() {
    return Collections.unmodifiableMap(_nextStateMap);
  }

  /**
   * Clean up the next state map;
   */
  public synchronized void clearNextStateMap() {
    _nextStateMap.clear();
  }
}
