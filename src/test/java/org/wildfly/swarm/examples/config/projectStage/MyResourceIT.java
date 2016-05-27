package org.wildfly.swarm.examples.config.projectStage;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.ContainerFactory;
import org.wildfly.swarm.arquillian.StartupTimeout;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

import static org.junit.Assert.assertTrue;

@StartupTimeout(100)
@RunWith(Arquillian.class)
public class MyResourceIT implements ContainerFactory {

  @Deployment(testable = false)
  public static JAXRSArchive createDeployment() {
    return MyDeployment.deployment();
  }

  @Override
  public Container newContainer(String... args) throws Exception {
    return MyContainer.newContainer(args);
  }

  @ArquillianResource
  private URI deploymentUri;

  @Test
  public void test() throws Exception {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(deploymentUri);

    Response response = target.request(MediaType.TEXT_PLAIN).get();
    String entity = response.readEntity(String.class);

    assertTrue(entity.contains("Howdy"));

    client.close();
  }

}