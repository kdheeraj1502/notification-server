package community.notificationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@SpringBootApplication(scanBasePackages = {"community.notificationserver.service",
		"community.notificationserver.service.impl", "community.notificationserver.configuration",
		"community.notificationserver.service"})*/
@SpringBootApplication
public class NotificationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServerApplication.class, args);
	}

}
