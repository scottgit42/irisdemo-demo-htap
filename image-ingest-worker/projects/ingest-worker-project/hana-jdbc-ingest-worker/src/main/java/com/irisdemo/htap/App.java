package com.irisdemo.htap;

import java.io.IOException;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.irisdemo.htap.config.Config;
import com.irisdemo.htap.config.ConfigService;

import java.util.concurrent.Executor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableScheduling
@SpringBootApplication
@Configuration
@EnableAsync
@ComponentScan({"com.irisdemo"})
public class App implements ApplicationRunner
{	
	Logger logger = LoggerFactory.getLogger(App.class);
	
	@Bean("workerExecutor")
    public Executor workerExecutor(){
        ThreadPoolTaskExecutor poolExecutor = new ThreadPoolTaskExecutor();
        poolExecutor.setCorePoolSize(20);
        poolExecutor.setMaxPoolSize(300);
        poolExecutor.setQueueCapacity(0);
        poolExecutor.setThreadNamePrefix("worker-");
        return poolExecutor;
	}

	/*
	 * This bean is used by the ConfigService to register with the master and get the configuration.
	 * Every service that needs to call a REST service, just needs to have a RestTemplate injected in
	 * order to do so.
	 */
    @Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) 
    {
    	return builder.build();
	}
    
	public static void main(String[] args) throws IOException
	{
		SpringApplication app = new SpringApplication(App.class);
		        
		ConfigurableApplicationContext ctx = app.run(args);
		
        /// This will terminate the app after run() is done.
		//ctx.close();
    }
    
	public void run(ApplicationArguments args) 
	{	
		try
		{
			//ui.run();
			// This will stop Spring
			//context.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
