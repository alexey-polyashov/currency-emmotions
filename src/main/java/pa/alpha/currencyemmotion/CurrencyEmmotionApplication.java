package pa.alpha.currencyemmotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableFeignClients
@PropertySource("classpath:secret.properties")
public class CurrencyEmmotionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyEmmotionApplication.class, args);
	}

}
