package ch.qc.starter;

import ch.cern.quantumconnect.core.Experiment;

public class EmulatorRunner {

  public static void main(String[] args) throws InterruptedException {
    var exp = new Experiment(new SpecialMoveBot(), new RandomBot());
    exp.runExperiment();
  }
}
