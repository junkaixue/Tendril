package org.sharding.tendril.datamodel.state;

import java.util.Map;

public interface StateModel {

  int getNumTopState();

  Map<ShardState, Integer> getStateCountMap(int replicationFactor, Map<ShardState, Integer> specialStates);


  }
