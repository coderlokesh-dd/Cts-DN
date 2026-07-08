package com.cognizant.springlearn.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Country;

@Service
public class CountryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

	public Country getCountry(String code) {
		LOGGER.info("Start of getCountry() method. code={}", code);
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		Map<String, Country> countryBeans = context.getBeansOfType(Country.class);

		Country result = countryBeans.values().stream()
				.filter(country -> country.getCode().equalsIgnoreCase(code))
				.findFirst()
				.orElse(null);

		LOGGER.info("End of getCountry() method. result={}", result);
		return result;
	}

}
