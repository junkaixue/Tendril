package org.sharding.tendril.controller.assignment;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.sharding.tendril.datamodel.configs.TendrilInstanceConfig;

/**
 * ShardAssignment is a data structure that holds the assignment of shards to instances and final
 * states of the shards.
 */
public class ShardAssignment {

  private final String _resourceName;
  private Map<String, Map<TendrilInstanceConfig, String>> _shardAssignmentMap;

  public ShardAssignment(String resourceName) {
    _resourceName = resourceName;
    _shardAssignmentMap = new HashMap<>();
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
   * Get the shard assignment map.
   *
   * @return the shard assignment map.
   */
  public Map<String, Map<TendrilInstanceConfig, String>> getShardAssignmentMap() {
    return Collections.unmodifiableMap(_shardAssignmentMap);
  }

  /**
   * Get the shard assignment map for a specific shard.
   *
   * @param shardName   the shard name.
   * @param instanceMap the instance map.
   */
  public synchronized void addShardAssignment(String shardName,
      Map<TendrilInstanceConfig, String> instanceMap) {
    _shardAssignmentMap.put(shardName, instanceMap);
  }

  /**
   * Get the shard assignment map for a specific shard.
   *
   * @param shardName the shard name.
   */
  public synchronized void removeShardAssignment(String shardName) {
    _shardAssignmentMap.remove(shardName);
  }

  /**
   * Clear the shard assignment map.
   */
  public synchronized void clearShardAssignment() {
    _shardAssignmentMap.clear();
  }

  /**
   * Update the shard assignment map.
   *
   * @param shardAssignmentMap the shard assignment map.
   */
  public synchronized void updateShardAssignment(
      Map<String, Map<TendrilInstanceConfig, String>> shardAssignmentMap) {
    _shardAssignmentMap = shardAssignmentMap;
  }
}
