package com.nttdata.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.junit.Assert;

public class PetStore {
    public String Base_URL;
    Response response;

    public String getBase_URL() {
        return Base_URL;
    }

    public String setBase_URL(String url) {
        return Base_URL = url;
    }

    public void solicitudBodyPost(String body) {
        response = RestAssured.given()
                .contentType("application/json")
                .body(body)
                .log().all()
                .post(Base_URL + body);
    }

    public void validoCodigoRespuesta(String codigoDeRespuesta) {
        int codigoRespuestaRecibido = Integer.parseInt(codigoDeRespuesta);
        if (response.getStatusCode() == codigoRespuestaRecibido){
            System.out.println("La orden fue creada correctamente: "+response.getStatusCode());
        }else{
            System.out.println("Error al crear la orden: "+response.getStatusCode());
        }
    }

    //GET

    public void obtenerID(String id) {
        response = RestAssured
                .given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .baseUri(this.Base_URL)
                .log().all()
                .when()
                .get("/store/order/"+id)
                .then()
                .log().all()
                .extract().response();
    }

    public void validacionCodigoRespuesta(String codigoRespuesta) {
        System.out.println("Validación del codigo de repuesta");
        int esperado = Integer.parseInt(codigoRespuesta);
        Assert.assertEquals("Los codigos son diferentes",esperado,response.getStatusCode());
    }

    public void validacionID(String id) {
        System.out.println("Validación del id de la consulta mediante el metodo GET");
        Assert.assertEquals("No coincide el id de la orden",id,response.jsonPath().getString("id"));
    }

}
