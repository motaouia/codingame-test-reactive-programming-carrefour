package org.medmota.demo.service;

import java.util.Date;
import java.util.UUID;

import org.medmota.demo.entities.DogApiResponse;
import org.medmota.demo.entities.Product;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	private WebClient webClient;

	public ProductService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.build();
	}

	private Mono<String> getRandomImagePath() {
		String apiUrl = "https://dog.ceo/api/breeds/image/random";

		return webClient.get().uri(apiUrl).accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToMono(DogApiResponse.class).map(DogApiResponse::imagePath);

	}

	public Mono<Product> generateProduct() {
		return getRandomImagePath().map(imagePath -> {
			UUID id = UUID.randomUUID();
			Date creationDate = new Date();
			return new Product(id, creationDate, imagePath);
		});
	}

	public Flux<Product> getProducts(int size) {
		return Flux.range(1, size).flatMap(i -> generateProduct());
	}
}