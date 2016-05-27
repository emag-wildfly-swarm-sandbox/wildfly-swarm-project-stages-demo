package org.wildfly.swarm.examples.config.projectStage;

/**
 * @author Heiko Braun
 * @since 10/11/15
 */
public class ProjectStagesMain {

  public static void main(String[] args) throws Exception {
    MyContainer.newContainer(args)
        .start()
        .deploy(MyDeployment.deployment());
  }

}
