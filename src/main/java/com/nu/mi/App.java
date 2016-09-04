package com.nu.mi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.nu.mi.reset.ResFulService;

import io.dropwizard.Application;
import io.dropwizard.jetty.ConnectorFactory;
import io.dropwizard.jetty.HttpConnectorFactory;
import io.dropwizard.jetty.HttpsConnectorFactory;
import io.dropwizard.server.DefaultServerFactory;
import io.dropwizard.server.ServerFactory;
import io.dropwizard.server.SimpleServerFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerDropwizard;

/**
 * Hello world!
 *
 */
public class App extends Application<MyConfig> 
{
	private final SwaggerDropwizard swaggerDropwizard = new SwaggerDropwizard();
	@Override
	public void initialize(Bootstrap<MyConfig> bootstrap) {
		swaggerDropwizard.onInitialize(bootstrap);
	}
	
    public static void main( String[] args )
    {
    	try {
			new App().run(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void run(MyConfig configuration, Environment environment) throws Exception {
		environment.jersey().register(new ResFulService());
		ConfigStor.setConfig(configuration);
		
		HttpConnectorFactory connector = getHttpConnectionFactory(configuration);
		swaggerDropwizard.onRun(configuration, environment, connector.getBindHost(), connector.getPort());
	}
	
	private HttpConnectorFactory getHttpConnectionFactory(MyConfig configuration) {
		List<ConnectorFactory> connectorFactories = getConnectorFactories(configuration);
		for (ConnectorFactory connectorFactory : connectorFactories) {
			if (connectorFactory instanceof HttpsConnectorFactory) {
				return (HttpConnectorFactory) connectorFactory; // if we find 
																// https skip
																// the others
			}
		}
		for (ConnectorFactory connectorFactory : connectorFactories) {
			if (connectorFactory instanceof HttpConnectorFactory) {
				return (HttpConnectorFactory) connectorFactory; // if not https
																// pick http
			}
		}

		throw new IllegalStateException("Unable to find an HttpServerFactory");
	}

	private List<ConnectorFactory> getConnectorFactories(MyConfig configuration) {
		ServerFactory serverFactory = configuration.getServerFactory();
		if (serverFactory instanceof SimpleServerFactory) {
			return Collections.singletonList(((SimpleServerFactory) serverFactory).getConnector());
		} else if (serverFactory instanceof DefaultServerFactory) {
			return new ArrayList<>(((DefaultServerFactory) serverFactory).getApplicationConnectors());
		} else {
			throw new IllegalStateException("Unknown ServerFactory implementation: " + serverFactory.getClass());
		}
	}
	
}
