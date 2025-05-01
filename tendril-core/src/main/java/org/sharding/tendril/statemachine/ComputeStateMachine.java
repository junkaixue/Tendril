package org.sharding.tendril.statemachine;

import org.sharding.tendril.datamodel.state.ShardState;
import org.sharding.tendril.datamodel.state.StateChangeContext;

/**
 * A state machine callback when a shard get a new state or changing.
 */
public interface ComputeStateMachine {

  /**
   * Invoke the state change with user implemented logic.
   *
   * @param context the state change context
   */
  void invoke(StateChangeContext context);


  /**
   * Get the current state of the shard.
   *
   * @return the current state of the shard
   */
  ShardState getCurrentState();

  /**
   * Get the resource name of the shard.
   *
   * @return the resource name of the shard
   */
  String getResourceName();

  /**
   * Get the shard name of the shard.
   *
   * @return the shard name of the shard
   */
  String getShardName();

}
