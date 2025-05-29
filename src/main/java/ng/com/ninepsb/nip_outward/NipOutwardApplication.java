package ng.com.ninepsb.nip_outward;

import ng.com.ninepsb.logger_lib.service.CustomLogger;
import ng.com.ninepsb.nip_outward.config.AppConfig;
import ng.com.ninepsb.nip_outward.dto.request.AccountEnquiryRequest;
import ng.com.ninepsb.nip_outward.dto.response.AccountEnquiryResponse;
import ng.com.ninepsb.nip_outward.handlers.NameEnquiryHandler;
import ng.com.ninepsb.nip_outward.service.NipOutwardService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableConfigurationProperties(AppConfig.class)
public class NipOutwardApplication {

	public static void main(String[] args) {
		SpringApplication.run(NipOutwardApplication.class, args);
	}

	@Bean
	CommandLineRunner init(NameEnquiryHandler handler, CustomLogger logger) {
		return args -> {
			AccountEnquiryRequest request = new AccountEnquiryRequest("0051762787", "999998");
			AccountEnquiryResponse response = handler.handle(request);
			System.out.println("response = " + response);
//			logger.logResponse(response.toString());
		};
	}

}
