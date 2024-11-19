package org.sharding.tendril.datamodel.state;

public class MultiLeaderStateModel extends AbstractStateModel {
  public MultiLeaderStateModel() {
    super(-1);
  }

  public MultiLeaderStateModel(int numTopState) {
    super(numTopState);
  }
}
