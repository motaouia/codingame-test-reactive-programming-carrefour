package org.medmota.demo.service;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.medmota.demo.entities.DogApiResponse;
import org.medmota.demo.entities.Product;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ProductServiceTest {
	@Mock
	private WebClient.Builder webClientBuilder;

	@Test
    public void generateProduct_ShouldReturnProduct() {
        // Initialize the mock objects
        MockitoAnnotations.initMocks(this);
        WebClient webClient = WebClient.create();
        when(webClientBuilder.build()).thenReturn(webClient);

        // Create a test instance of ProductService
        ProductService productService = new ProductService(webClientBuilder);

        // Define the expected result
        String expectedImagePath = "https://example.com/image.jpg";
        UUID expectedId = UUID.randomUUID();
        Date expectedCreationDate = new Date();

        doReturn(Mono.just(new DogApiResponse(expectedImagePath)))
        	.when(webClient).get()
        	.uri("https://dog.ceo/api/breeds/image/random")
        	.retrieve().bodyToMono(DogApiResponse.class);

        // Call the generateProduct() method and verify the result
        Mono<Product> result = productService.generateProduct();
        StepVerifier.create(result)
                .expectNextMatches(product -> product.id().equals(expectedId)
                        && product.creationDate().equals(expectedCreationDate)
                        && product.imagePath().equals(expectedImagePath))
                .verifyComplete();
    }

	@Test
	public void getProducts_ShouldReturnFluxOfProducts() {
		// Initialize the mock objects
		MockitoAnnotations.initMocks(this);
		WebClient webClient = WebClient.create();
		when(webClientBuilder.build()).thenReturn(webClient);

		// Create a test instance of ProductService
		ProductService productService = new ProductService(webClientBuilder);

		// Define the expected size
		int size = 3;

		// Mock the getRandomImagePath() method to return a fixed image path
		when(webClient.get().uri("https://dog.ceo/api/breeds/image/random").retrieve().bodyToMono(DogApiResponse.class))
				.thenReturn(Mono.just(new DogApiResponse("https://example.com/image.jpg")));

		// Call the getProducts() method and verify the result
		Flux<Product> result = productService.getProducts(size);
		StepVerifier.create(result).expectNextCount(size).verifyComplete();
	}
}
