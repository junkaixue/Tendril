package org.sharding.tendril.controller.assignment;

import org.sharding.tendril.cache.ClusterCache;

public interface ShardAssigner {

  ShardAssignment compute(ClusterCache clusterCache);

}
