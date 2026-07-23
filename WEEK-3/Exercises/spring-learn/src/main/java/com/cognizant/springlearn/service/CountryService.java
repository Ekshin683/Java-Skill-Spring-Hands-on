package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CountryService.class);

    // Returns all countries from country.xml
    @SuppressWarnings("unchecked")
    public List<Country> getAllCountries() {
        LOGGER.info("Start");
        ApplicationContext context =
                new ClassPathXmlApplicationContext("country.xml");
        List<Country> countries = context.getBean("countryList", List.class);
        LOGGER.debug("Countries: {}", countries);
        LOGGER.info("End");
        return countries;
    }

    // Returns single country by code (case-insensitive)
    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("Start");
        LOGGER.debug("Looking for code: {}", code);

        List<Country> countries = getAllCountries();

        // Lambda expression with case-insensitive match
        Country found = countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);

        if (found == null) {
            LOGGER.warn("Country not found for code: {}", code);
            throw new CountryNotFoundException();
        }

        LOGGER.debug("Found: {}", found);
        LOGGER.info("End");
        return found;
    }
}