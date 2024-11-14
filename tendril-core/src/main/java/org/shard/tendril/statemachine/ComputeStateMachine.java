package org.shard.tendril.statemachine;

import org.shard.tendril.datamodel.StateChangeContext;

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
}
