package org.wildfly.swarm.examples.config.projectStage;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Bob McWhirter
 */
@Path("/")
public class MyResource {

  @GET
  @Produces("text/plain")
  public String get() throws NamingException, SQLException {
    Context ctx = new InitialContext();
    DataSource ds = (DataSource) ctx.lookup("jboss/datasources/ExampleDS");
    try (Connection conn = ds.getConnection()) {
      return "Howdy using connection url: " + conn.getMetaData().getURL();
    }
  }

}
