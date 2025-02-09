package community.notificationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/*@SpringBootApplication(scanBasePackages = {"community.notificationserver.service",
		"community.notificationserver.service.impl", "community.notificationserver.configuration",
		"community.notificationserver.service"})*/
@SpringBootApplication
public class NotificationServerApplication {
 int arr[] = {5, 6};

	public static void main(String[] args) {
		SpringApplication.run(NotificationServerApplication.class, args);


		List<int[]> aux = new ArrayList<>();
		aux.add(new int[]{3, 4});
	}

}
