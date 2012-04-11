package org.jboss.arquillian.showcase.guice.jpa;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * <p>Defines data source within the container.</p>
 *
 * @author <a href="mailto:jmnarloch@gmail.com">Jakub Narloch</a>
 */
@DataSourceDefinition(name = "GuiceTestDataSource",
        className = "org.hsqldb.jdbcDriver",
        url = "jdbc:hsqldb:.",
        user = "sa",
        password = "")
@Singleton
@Startup
public class GuiceTestDataSourceEJB {
}
