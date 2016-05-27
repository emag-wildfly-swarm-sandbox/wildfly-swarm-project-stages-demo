package org.wildfly.swarm.examples.config.projectStage;

import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.datasources.DatasourcesFraction;

public class MyContainer {

  public static Container newContainer(String[] args) throws Exception {
    Container container = new Container(args);

    System.err.println("Connection URL: " + container.stageConfig().resolve("database.connection.url").getValue());

    container.fraction(
        new DatasourcesFraction()
            .jdbcDriver("h2", (d) -> {
              d.driverClassName("org.h2.Driver");
              d.xaDatasourceClass("org.h2.jdbcx.JdbcDataSource");
              d.driverModuleName("com.h2database.h2");
            })
            .dataSource("ExampleDS", (ds) -> {
              ds.driverName("h2");
              ds.connectionUrl(
                  container
                      .stageConfig()
                      .resolve("database.connection.url")
                      .getValue()
              );
              ds.userName("sa");
              ds.password("sa");
            })
    );

    return container;
  }

}
