package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    public CountryController() {
        LOGGER.info("CountryController instantiated by Spring");
    }

    // GET /country — returns India bean loaded from country.xml
    @RequestMapping("/country")
    public Country getCountryIndia() {
        LOGGER.info("Start");
        ApplicationContext context =
                new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("country", Country.class);
        LOGGER.debug("Returning: {}", country);
        LOGGER.info("End");
        return country;
    }
    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("Countries count: {}", countries.size());
        LOGGER.info("End");
        return countries;
    }
    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code)
            throws CountryNotFoundException {
        LOGGER.info("Start");
        LOGGER.debug("Requested code: {}", code);
        Country country = countryService.getCountry(code);
        LOGGER.info("End");
        return country;
    }
}