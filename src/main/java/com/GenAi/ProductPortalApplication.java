package com.GenAi;

import com.GenAi.utility.SSLUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductPortalApplication {

	public static void main(String[] args) throws Exception {
		SSLUtil.disableCertificateValidation(); // ⚠️ dev only
		SpringApplication.run(ProductPortalApplication.class, args);
	}

}
