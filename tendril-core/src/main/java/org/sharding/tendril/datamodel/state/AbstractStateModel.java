package org.sharding.tendril.datamodel.state;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class for state model with state of {@link ShardState}.
 * <p>
 * User can define number of top states and replication factor. If number of top states is greater
 * than replication factor, replication factor will be used.
 */
public abstract class AbstractStateModel implements StateModel {
  private static final Logger LOG = LoggerFactory.getLogger(AbstractStateModel.class);
  protected final int _numTopState;

  public AbstractStateModel(int numTopState) {
    _numTopState = numTopState;
    if (_numTopState < 0) {
      throw new IllegalArgumentException(
          "numTopState must be equal (same as replication factor) or greater than 0");
    }
  }

  @Override
  public int getNumTopState() {
    return _numTopState;
  }

  @Override
  public Map<ShardState, Integer> getStateCountMap(int replicationFactor,
      Map<ShardState, Integer> specialStates) {
    int localNumTopState = _numTopState;
    if (_numTopState == 0 || _numTopState > replicationFactor) {
      LOG.warn(
          "numTopState either not setup or must be less than or equal to replication factor, use replication factor instead");
      localNumTopState = replicationFactor;
    }

    int specialStateCount = specialStates.values().stream().mapToInt(Integer::intValue).sum();
    if (specialStateCount > replicationFactor) {
      LOG.warn(
          "specialStates must be less than or equal to replication factor, otherwise, no more states are allowed");
      return specialStates;
    }

    int adjustedTopState = Math.max(replicationFactor - localNumTopState,
        0);

    Map<ShardState, Integer> result = new HashMap<>(specialStates);
    result.put(ShardState.LEADER, adjustedTopState);
    if ((adjustedTopState + specialStateCount) < replicationFactor) {
      result.put(ShardState.FOLLOWER, replicationFactor - specialStateCount - adjustedTopState);
    }
    return result;
  }
}
