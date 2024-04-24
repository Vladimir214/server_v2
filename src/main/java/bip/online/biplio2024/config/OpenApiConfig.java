package bip.online.biplio2024.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "Мой сервер",
        description = "Электронная библиотека", version = "1.0.0",
        contact = @Contact(
            name = "Voronovich Vladimir",
            email = "vovavoronovic72@gmail.com"
        )
    )
)
public class OpenApiConfig {
}
