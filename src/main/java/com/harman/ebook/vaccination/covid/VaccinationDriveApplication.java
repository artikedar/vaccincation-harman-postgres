package com.harman.ebook.vaccination.covid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class VaccinationDriveApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaccinationDriveApplication.class, args);
	}

}
