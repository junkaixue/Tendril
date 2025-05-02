package org.sharding.tendril.datamodel.configs;

import org.apache.helix.model.InstanceConfig;

public class TendrilInstanceConfig extends InstanceConfig {

  public TendrilInstanceConfig(String instanceId) {
    super(instanceId);
  }
}
