package org.sharding.tendril.controller.planner;

import org.sharding.tendril.controller.assignment.ShardAssignment;

/**
 * ShardPlanner is responsible for planning the strategy to move shards around.
 * Multiple implementations of ShardPlanner can be provided to the TendrilController.
 */
public interface ShardPlanner {

  /**
   * Plan the steps to move shards around.
   * @param shardAssignment The current assignment of shards.
   * @return The planned actions to move shards assignment.
   */
  PlannedActions plan(ShardAssignment shardAssignment);
}
