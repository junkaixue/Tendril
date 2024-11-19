package org.sharding.tendril.datamodel;

import java.util.HashMap;
import java.util.Map;
import org.sharding.tendril.datamodel.state.AbstractStateModel;
import org.sharding.tendril.datamodel.state.MultiLeaderStateModel;
import org.sharding.tendril.datamodel.state.ShardState;
import org.sharding.tendril.datamodel.state.SingleLeaderStateModel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDataModel {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testStateModels() {
    SingleLeaderStateModel singleLeaderStateModel = new SingleLeaderStateModel();
    Assert.assertEquals(1, singleLeaderStateModel.getNumTopState());

    MultiLeaderStateModel multiLeaderStateModel = new MultiLeaderStateModel();
    Assert.assertEquals(0, multiLeaderStateModel.getNumTopState());

    AbstractStateModel abstractStateModel = new MultiLeaderStateModel(3);
    Assert.assertEquals(3, abstractStateModel.getNumTopState());

    // Test getStateCountMap
    Map<ShardState, Integer> specialStates = Map.of(ShardState.OFFLINE, 1, ShardState.ERROR, 2);
    // 3 special states take all the states
    Assert.assertEquals(specialStates, singleLeaderStateModel.getStateCountMap(3, specialStates));
    Map<ShardState, Integer> expectedResult = new HashMap<>(specialStates);
    expectedResult.put(ShardState.LEADER, 2);
    // 3 special states + 2 leader states = 5 states
    Assert.assertEquals(expectedResult, singleLeaderStateModel.getStateCountMap(5, specialStates));
    expectedResult.put(ShardState.LEADER, 3);
    expectedResult.put(ShardState.FOLLOWER, 2);
    // 3 special states + 3 leader states + 2 follower states = 8 states
    Assert.assertEquals(expectedResult, singleLeaderStateModel.getStateCountMap(8, specialStates));

    // Exception case
    new MultiLeaderStateModel(-1);
  }
}
