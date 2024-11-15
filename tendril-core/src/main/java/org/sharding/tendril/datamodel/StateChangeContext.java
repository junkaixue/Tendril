package org.sharding.tendril.datamodel;

import java.util.Optional;

/**
 * Context for a state change operation.
 *
 * @param resourceId resource for which the state change is happening
 * @param shardId    shard for which the state change is happening
 * @param operation  operation that is being performed
 * @param finalState the final of this shard should be in after the operation
 */
public record StateChangeContext(
    String resourceId,                    // Resource ID cannot be null
    String shardId,                       // Shard ID cannot be null
    ChangeOperation operation,
    // Operation cannot be null and for add/remove operations, shard state can be null
    Optional<ShardState> finalState) {     // To state must be present for CHANGE_STATE operation. It may not
  // real final state if it is leader/follower mode. It can be intermediate state to guarantee the single
  // leader in the  cluster.

  public StateChangeContext {
    if (resourceId == null) {
      throw new IllegalArgumentException("resourceId cannot be null");
    }
    if (shardId == null) {
      throw new IllegalArgumentException("shardId cannot be null");
    }
    if (operation == null) {
      throw new IllegalArgumentException("operation cannot be null");
    }
    if (ChangeOperation.CHANGE_STATE.equals(operation) && !finalState.isPresent()) {
      throw new IllegalArgumentException("toState cannot be null for operation CHANGE_STATE");
    }
  }
}
