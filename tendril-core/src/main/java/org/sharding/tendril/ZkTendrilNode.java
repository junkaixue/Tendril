package org.sharding.tendril;

/**
 * Tendril node that connects to a Zookeeper cluster.
 * <p>
 * The lifecycle of a ZkTendrilNode will be managed by ZK heartbeats.
 */
public class ZkTendrilNode extends AbstractTendrilNode {

  private final String _clusterName;
  private final String _instanceName;
  private final String _zkConnectString;

  public ZkTendrilNode(String clusterName, String instanceName, String zkConnectString) {
    super();

    _clusterName = clusterName;
    _instanceName = instanceName;
    _zkConnectString = zkConnectString;
  }

  @Override
  public void connect() {

  }

  @Override
  public void disconnect() {

  }
}
