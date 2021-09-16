package com.spring.webflux.reactive.repository;

import com.spring.webflux.reactive.model.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class HighwayUtilities {
    private static DecimalFormat plateFormatNumber = new DecimalFormat("0000");

    private static String[] COLORS = { "Blue", "White", "Silver", "Black", "Metalic Green", "Orange", "Yellow" };
    private static String[] GAS_TYPE = { "Diesel", "Gasoline", "Gas", "Eletric", "Alcohol" };

    @Scheduled(fixedRate = 5000)
    public  Vehicle simulateTraffic() {
        RandomStringGenerator rndStringGen = new RandomStringGenerator.Builder().withinRange('A', 'Z').build();

        StringBuffer sb = new StringBuffer(rndStringGen.generate(3).toUpperCase());
        sb.append(" ");
        sb.append(plateFormatNumber.format(ThreadLocalRandom.current().nextInt(0, 9999)));
        String carPlateNumber = sb.toString();

        Long weight = ThreadLocalRandom.current().nextLong(250L, 4500L);
        Integer speed = ThreadLocalRandom.current().nextInt(60, 175);

        String color = COLORS[ThreadLocalRandom.current().nextInt(0, 6)];
        Integer modelYear = ThreadLocalRandom.current().nextInt(1975, 2018);
        String gasType = GAS_TYPE[ThreadLocalRandom.current().nextInt(0, 4)];
        log.info("plate number: " + carPlateNumber + " Color: " + color);
        return new Vehicle(carPlateNumber, weight, speed, color, modelYear, gasType);
    }

}
