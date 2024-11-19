package org.sharding.tendril.datamodel.configs;

import org.sharding.tendril.datamodel.state.StateModel;

public record Resource(String resourceName,
                       int numShards,
                       int replicationFactor,
                       StateModel stateModel
                       ) {

}
