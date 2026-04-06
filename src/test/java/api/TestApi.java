package api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestApi {

    @Test
    void testParse() throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(CalculatorApiApplication.class);

        try {
            HttpClient client = HttpClient.newHttpClient();

            String jsonBody = """
                {
                    "input": "299*3.297465",
                    "angleMode": "RAD",
                    "precision": 6
                }
                """;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/calculator/parse"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            assertEquals(200, response.statusCode());
            System.out.println("API Res : " + response.body());

        } finally {
            context.close();
        }
    }

    @Test
    void testConvertResult() throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(CalculatorApiApplication.class);

        try {
            HttpClient client = HttpClient.newHttpClient();

            String jsonBody = """
                {
                    "result": "0.125",
                    "precision": 6
                }
                """;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/calculator/convert-result"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            assertEquals(200, response.statusCode());
            System.out.println("Convert API Res : " + response.body());

        } finally {
            context.close();
        }
    }
}