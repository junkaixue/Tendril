package org.shard.tendril;

import org.shard.tendril.statemachine.ComputeStateMachineFactory;

/**
 * Interface for a Tendril node.
 */
public interface TendrilNode {

  /**
   * Connect to the Tendril cluster.
   */
  void connect();

  /**
   * Disconnect from the Tendril cluster.
   */
  void disconnect();

  /**
   * Register a compute state machine factory.
   *
   * @param factory the factory to create compute state machine instances
   */
  void registerComputeStateMachineFactory(ComputeStateMachineFactory factory);
}
