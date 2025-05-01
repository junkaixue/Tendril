package org.sharding.tendril;

import org.sharding.tendril.statemachine.ComputeStateMachineFactory;

public abstract class AbstractTendrilNode implements TendrilNode {
  protected final ComputeStateMachineFactory computeStateMachineFactory;

  public AbstractTendrilNode(ComputeStateMachineFactory computeStateMachineFactory) {
    this.computeStateMachineFactory = computeStateMachineFactory;
  }
}
