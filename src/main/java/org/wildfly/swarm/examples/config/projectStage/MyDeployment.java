package org.wildfly.swarm.examples.config.projectStage;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

public class MyDeployment {

  public static JAXRSArchive deployment() {
    JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
    deployment.addPackage(ProjectStagesMain.class.getPackage());

    return deployment;
  }

}
