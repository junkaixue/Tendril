package org.sharding.tendril.cache;

import java.util.Map;
import org.sharding.tendril.controller.assignment.ShardAssignment;
import org.sharding.tendril.controller.planner.PlannedActions;
import org.sharding.tendril.datamodel.configs.TendrilClusterConfig;
import org.sharding.tendril.datamodel.configs.TendrilInstanceConfig;

public interface TendrilClusterCache {

    void start();

    void stop();

    TendrilClusterConfig getTendrilClusterConfig();

    TendrilInstanceConfig getTendrilInstanceConfig(String instanceName);

    Map<String, ShardAssignment> getShardAssignments();

    Map<String, PlannedActions> getPlannedActions();

    void refresh();
}
