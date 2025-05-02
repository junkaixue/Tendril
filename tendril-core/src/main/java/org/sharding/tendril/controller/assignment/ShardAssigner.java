package org.sharding.tendril.controller.assignment;

import org.sharding.tendril.cache.TendrilClusterCache;

/**
 * ShardAssigner is responsible for assigning shards to nodes in the cluster.
 */
public interface ShardAssigner {

  /**
   * Compute the shard assignment for the cluster.
   *
   * @param clusterCache The cache containing the current state of the cluster.
   * @return The computed shard assignment.
   */
  ShardAssignment compute(TendrilClusterCache clusterCache);
}
