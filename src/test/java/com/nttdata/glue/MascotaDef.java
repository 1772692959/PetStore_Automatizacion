package com.nttdata.glue;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MascotaDef {

    private String URL_BASE;
    private String endpoint;
    private int responseCode;
    private String responseBody;

    @Given("defino la URL base de la api {string}")
    public void defino_la_url_base_de_la_api(String url) {
        URL_BASE = url;
    }

    @Given("creacion de Order POST {string}")
    public void creacion_de_order_post(String path) {
        endpoint = path;
    }

    @When("envia una peticion con el body:")
    public void envia_una_peticion_con_el_body(String body) throws Exception {
        URL url = new URL(URL_BASE + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(body.getBytes());
            os.flush();
        }

        responseCode = conn.getResponseCode();
        responseBody = new Scanner(conn.getInputStream()).useDelimiter("\\A").next();
        conn.disconnect();
    }

    @Given("consulta de Order GET \"{string}\"")
    public void consulta_de_order_get(String id) {
        endpoint = "/store/order/" + id;
    }

    @When("se realiza la peticion para obtener el pedido")
    public void se_realiza_la_peticion_para_obtener_el_pedido() throws Exception {
        URL url = new URL(URL_BASE + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");

        responseCode = conn.getResponseCode();
        responseBody = new Scanner(conn.getInputStream()).useDelimiter("\\A").next();
        conn.disconnect();
    }

    @Then("valido el codigo de respuesta sea {int}")
    public void valido_el_codigo_de_respuesta_sea(Integer statusCode) {
        assertEquals((int) statusCode, responseCode);
    }

    @And("valido que el body contenga:")
    public void valido_que_el_body_contenga(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> row : rows) {
            String key = row.get(0);
            String value = row.get(1);
            assertTrue(responseBody.contains("\"" + key + "\":" + formatValue(value)));
        }
    }

    private String formatValue(String value) {
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            return value;
        }
        try {
            Integer.parseInt(value);
            return value;
        } catch (NumberFormatException e) {
            return "\"" + value + "\"";
        }
    }
}
