package org.sharding.tendril.datamodel.configs;

import org.apache.helix.model.ClusterConfig;

public class TendrilClusterConfig extends ClusterConfig {
    public TendrilClusterConfig(String clusterName) {
        super(clusterName);
    }
}
