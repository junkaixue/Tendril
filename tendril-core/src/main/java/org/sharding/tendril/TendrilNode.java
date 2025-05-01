package org.sharding.tendril;

import org.sharding.tendril.statemachine.ComputeStateMachineFactory;

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
}
