package ng.com.ninepsb.nip_outward;

import ng.com.ninepsb.nip_outward.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableConfigurationProperties(AppConfig.class)
public class NipOutwardApplication {

	public static void main(String[] args) {
		SpringApplication.run(NipOutwardApplication.class, args);
	}

}
