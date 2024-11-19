package org.sharding.tendril.datamodel.state;

/**
 * State model for a single leader for a shard. Usually used in read-write stateful services.
 */
public class SingleLeaderStateModel extends AbstractStateModel {
  public SingleLeaderStateModel() {
    super(1);
  }
}
