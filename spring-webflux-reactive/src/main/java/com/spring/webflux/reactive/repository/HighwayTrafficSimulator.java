package com.spring.webflux.reactive.repository;

import com.spring.webflux.reactive.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class HighwayTrafficSimulator implements HighwayTraffic {

	private static Logger LOGGER = LoggerFactory.getLogger(HighwayTrafficSimulator.class);
//	private static DecimalFormat plateFormatNumber = new DecimalFormat("0000");

	@Autowired
	com.spring.webflux.reactive.repository.HighwayUtilities highwayUtilities;

	public HighwayTrafficSimulator() {
	}

	public Flux<Vehicle> flowTraffic( String color) {
		LocalDateTime startTime = LocalDateTime.now();

		return Flux.<Vehicle>create(fluxSink -> {
			boolean inFrameTime = true;
			int index = 1;
			LOGGER.info("TrafficSimulator start --> With timer");
			while (index <= 30000 && inFrameTime && !fluxSink.isCancelled() ) {
				fluxSink.next(highwayUtilities.simulateTraffic());
				index++;
				long timeMinutesHighwayOpened = startTime.until(LocalDateTime.now(), 
						ChronoUnit.MILLIS);
				if (timeMinutesHighwayOpened > 30000) {
					LOGGER.info("TrafficSimulator finish --> With timer");
					inFrameTime = false;
				}
			}
		})
				.filter(s -> s.getColor().equals(color)).share();
	}

//	private static String[] COLORS = { "Blue", "White", "Silver", "Black", "Metalic Green", "Orange", "Yellow" };
//	private static String[] GAS_TYPE = { "Diesel", "Gasoline", "Gas", "Eletric", "Alcohol" };
//
//	private static class HighwayUtilities {
//
//		private static Vehicle simulateTraffic() {
//			RandomStringGenerator rndStringGen = new RandomStringGenerator.Builder().withinRange('A', 'Z').build();
//
//			StringBuffer sb = new StringBuffer(rndStringGen.generate(3).toUpperCase());
//			sb.append(" ");
//			sb.append(plateFormatNumber.format(ThreadLocalRandom.current().nextInt(0, 9999)));
//			String carPlateNumber = sb.toString();
//
//			Long weight = ThreadLocalRandom.current().nextLong(250L, 4500L);
//			Integer speed = ThreadLocalRandom.current().nextInt(60, 175);
//
//			String color = COLORS[ThreadLocalRandom.current().nextInt(0, 6)];
//			Integer modelYear = ThreadLocalRandom.current().nextInt(1975, 2018);
//			String gasType = GAS_TYPE[ThreadLocalRandom.current().nextInt(0, 4)];
//			LOGGER.info("plate number: " + carPlateNumber + " Color: " + color);
//			return new Vehicle(carPlateNumber, weight, speed, color, modelYear, gasType);
//		}
//
//	}

}
