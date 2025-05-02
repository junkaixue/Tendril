package org.sharding.tendril.controller;

public abstract class AbstractTendrilController implements TendrilController {

  @Override
  public void start() {
    // Default implementation can be overridden by subclasses
    System.out.println("Starting Tendril Controller...");
  }

  @Override
  public void stop() {
    // Default implementation can be overridden by subclasses
    System.out.println("Stopping Tendril Controller...");
  }

  // Additional common methods and properties can be added here
}
