package configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;




@Configuration
public class SweggerConfig {

	@Bean
	public OpenAPI springConectandoAPI() {
		return new OpenAPI()
				.info(new Info()
					.title("Conectando")
					.description("TCC - Euripedes")
					.license(new License()
									.name("PEI Euripedes Simões de Paula"))
		);
	}
	
	private ApiResponse createApiResponse(String message) {
		return new ApiResponse().description(message);
	}
	
	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
		return OpenApiCustomiser -> {
			OpenApiCustomiser.getPaths().values().forEach(
													PathItem -> PathItem.readOperations().forEach(
															operation -> {
																io.swagger.v3.oas.models.responses.ApiResponses apiResponses = operation.getResponses();
																
																apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
															}));
		};
	}

}
