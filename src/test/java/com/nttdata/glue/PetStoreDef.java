package com.nttdata.glue;

import com.nttdata.steps.PetStore;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import net.thucydides.core.annotations.Steps;

public class PetStoreDef {

    @Steps
    PetStore tienda;

    @Given("defino la URL de la tienda {string}")
    public void definoLaURLDeLaTienda(String url) {
        tienda.setBase_URL(url);
    }

    @When("envia una solicitud POST con el siguiente body:")
    public void enviaUnaSolicitudPOSTConElSiguienteBody(String body) {
        tienda.solicitudBodyPost(body);
    }

    @Then("valido el codigo de respuesta es <codEstado>")
    public void validoElCodigoDeRespuestaEsCodEstado(String CodigoEstado) {
        tienda.validoCodigoRespuesta(CodigoEstado);
    }

    @And("valido el id sea <id>")
    public void validoElIdSeaId(String IDvalido) {
        tienda.validoCodigoRespuesta(IDvalido);
    }

    //GET

    @When("envia una solicitud GET")
    public void enviaUnaSolicitudGET(String id) {
        tienda.obtenerID(id);

    }

    @Then("valido el codigo de respuesta es <codRespuesta>")
    public void validoElCodigoDeRespuestaEsCodRespuesta(String CodigoRespuesta) {
        tienda.validacionCodigoRespuesta(CodigoRespuesta);

    }

    @And("valido el id con <id>")
    public void validoElIdConId(String id) {
        tienda.validacionID(id);

    }



}
