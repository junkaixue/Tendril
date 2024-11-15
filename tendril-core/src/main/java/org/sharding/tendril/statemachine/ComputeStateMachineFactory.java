package org.sharding.tendril.statemachine;

/**
 * Factory for creating a {@link ComputeStateMachine}.
 */
public interface ComputeStateMachineFactory<T extends ComputeStateMachine> {

  /**
   * Create a new {@link ComputeStateMachine}.
   *
   * @return a new {@link ComputeStateMachine}
   */
  T createComputeStateMachine();
}
