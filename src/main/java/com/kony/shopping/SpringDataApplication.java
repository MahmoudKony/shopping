package com.kony.shopping;


import com.kony.shopping.user.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringDataApplication {

    @Autowired
    AddressRepository addressRepository;

    public static void main(String[] args) {

        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Bean
    public StringListConverter stringListConverter() {
        return new StringListConverter();
    }





}

class StringListConverter implements
        Converter<String, List<String>> {

    @Override
    public List<String> convert(String source) {
        return Arrays.asList(source.split(","));
    }


}
