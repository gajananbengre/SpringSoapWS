package com.gaja;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gaja.country.stubs.Country;
import com.gaja.country.stubs.Currency;
import com.gaja.model.CountryMO;
import com.gaja.repo.CountryRepository;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private CountryRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        
        CountryMO spain = new CountryMO();
		spain.setName("Spain");
		spain.setCapital("Madrid");
		spain.setCurrency(Currency.EUR);
		spain.setPopulation(46704314);

		repository.save(spain);

		CountryMO poland = new CountryMO();
		poland.setName("Poland");
		poland.setCapital("Warsaw");
		poland.setCurrency(Currency.PLN);
		poland.setPopulation(38186860);

		repository.save(poland);
		
		
		CountryMO uk = new CountryMO();
		uk.setName("UK");
		uk.setCapital("London");
		uk.setCurrency(Currency.GBP);
		uk.setPopulation(63705000);
		
		repository.save(uk);


        repository.findAll().forEach((country) -> {
            logger.info("{}", country.toString());
        });
    }
}