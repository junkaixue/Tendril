package org.sharding.tendril.statemachine;

import org.sharding.tendril.datamodel.state.ShardState;

public abstract class AbstractComputeStateMachine implements ComputeStateMachine {
  protected ShardState _currentState;
  protected final String _resourceName;
  protected final String _shardName;

  public AbstractComputeStateMachine(String resourceName, String shardName) {
    _currentState = ShardState.OFFLINE;
    _resourceName = resourceName;
    _shardName = shardName;
  }

  @Override
  public ShardState getCurrentState() {
    return _currentState;
  }

  @Override
  public String getResourceName() {
    return _resourceName;
  }

  @Override
  public String getShardName() {
    return _shardName;
  }
}
