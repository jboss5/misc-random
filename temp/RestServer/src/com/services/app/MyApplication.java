package com.services.app;

import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig
{
	public MyApplication()
	{
		packages("com.services");
	}
}