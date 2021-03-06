package com.spring.webflux.reactive.repository;

import com.spring.webflux.reactive.model.Vehicle;

import reactor.core.publisher.Flux;

public interface HighwayTraffic {
	
	Flux<Vehicle> flowTraffic( String color);

}