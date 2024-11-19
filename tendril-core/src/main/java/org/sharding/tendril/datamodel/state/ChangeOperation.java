package org.sharding.tendril.datamodel.state;

/**
 * List of change operations that can be performed on a shard.
 */
public enum ChangeOperation {
  ADD_SHARD,  // Add as a new shard to the cluster
  REMOVE_SHARD, // Remove this shard from the cluster
  CHANGE_STATE, // Change the state of specific shard
}
