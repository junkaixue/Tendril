package org.sharding.tendril.statemachine;

import org.sharding.tendril.datamodel.state.ChangeOperation;
import org.sharding.tendril.datamodel.state.ShardState;
import org.sharding.tendril.datamodel.state.SingleLeaderStateModel;
import org.sharding.tendril.datamodel.state.StateChangeContext;
import org.sharding.tendril.datamodel.state.StateModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleSingleLeaderComputeStateMachine extends AbstractComputeStateMachine {

  private static final Logger LOG = LoggerFactory.getLogger(
      ExampleSingleLeaderComputeStateMachine.class);
  private final StateModel _stateModel; // This model helps validate the state transition and
  // defining number of replicas for each state

  public ExampleSingleLeaderComputeStateMachine(String resourceName, String shardName) {
    super(resourceName, shardName);
    _stateModel = new SingleLeaderStateModel();
  }


  @Override
  public void invoke(StateChangeContext context) {
    if (!context.operation().equals(ChangeOperation.CHANGE_STATE)) {
      throw new IllegalArgumentException("Unknown operation: " + context.operation());
    }

    // Perform adding a shard, remove a shard, or change state
    switch (context.operation()) {
      case ADD_SHARD:
        LOG.info("Adding shard for resource {} with shardId {}", context.resourceId(),
            context.shardId());
        break;
      case REMOVE_SHARD:
        LOG.info("Removing shard for resource {} with shardId {}", context.resourceId(),
            context.shardId());
        break;
      case CHANGE_STATE:
        handleStateChange(context, _currentState);
        break;
      default:
        throw new IllegalArgumentException("Unknown operation: " + context.operation());
    }
  }

  /**
   * Handle state change for the shard. If state matches the final state, do nothing.
   * If state does not match the final state, update the state with user customized logic.
   * @param context StateChangeContext
   * @param state ShardState
   */
  private void handleStateChange(StateChangeContext context, ShardState state) {
    if (context.finalState().isEmpty()) {
      LOG.error("Final state is not present for resource {} with shardId {}", context.resourceId(),
          context.shardId());
      return;
    }

    if (context.finalState().get().equals(state)) {
      LOG.warn("Resource {} Shard {} is already {}", context.resourceId(),
          context.shardId(), state);
      return;
    }

    switch (_currentState) {
      case LEADER:
        if (context.finalState().get().equals(ShardState.FOLLOWER)) {
          // Step down from LEADER to FOLLOWER
          LOG.info("Step down from LEADER to FOLLOWER for resource {} with shardId {}",
              context.resourceId(), context.shardId());
        } else {
          // Shutdown the replica
         LOG.info("Step down from Leader directly to OFFLINE for resource {} with shardId {}",
              context.resourceId(), context.shardId());
        }
        break;
      case FOLLOWER:
        if (context.finalState().get().equals(ShardState.LEADER)) {
          // Promote from FOLLOWER to LEADER to do single leader work
          LOG.info("Promote from FOLLOWER to LEADER for resource {} with shardId {}",
              context.resourceId(), context.shardId());
        } else {
          // Shutdown the replica
          LOG.info("Step down from FOLLOWER to OFFLINE for resource {} with shardId {}",
              context.resourceId(), context.shardId());
        }
        break;
      case OFFLINE:
        if (context.finalState().get().equals(ShardState.LEADER)) {
          // Promote from OFFLINE to LEADER to do single leader work
          // Should include the operation from OFFLINE to FOLLOWER such as data bootstrapping
          LOG.info("Promote from OFFLINE to LEADER for resource {} with shardId {}",
              context.resourceId(), context.shardId());
        } else if (context.finalState().get().equals(ShardState.FOLLOWER)) {
          // Promote from OFFLINE to FOLLOWER with data bootstrapping
          LOG.info("Promote from OFFLINE to FOLLOWER for resource {} with shardId {}",
              context.resourceId(), context.shardId());
        } else {
          // Shutdown the replica
          LOG.info("Step down from OFFLINE to OFFLINE for resource {} with shardId {}",
              context.resourceId(), context.shardId());
        }
        break;
    }

  }
}
