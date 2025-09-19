@PruebaAPI
Feature: Validacion de API Store de PetStore
  COMO automatizador de NTTDATA
  QUIERO validar la integridad de creacion y consulta de pedidos en la tienda PetStore
  PARA garantizar el funcionamiento y validación de la conexion de la BD

  Background:
    Given defino la URL de la tienda "https://petstore.swagger.io/v2"

  @CreacionMascota
  Scenario Outline: Creacion de un nuevo pedido en PetStore
    When envia una solicitud POST con el siguiente body:
      """{
      "id":<id>,
      "petId":<petID>,
      "quantity":<quantity>,
      "shipDate":"<shipDate>",
      "status":"<status>",
      "complete":<complete>
      }
      """
    Then valido el codigo de respuesta es <codEstado>
    And valido el id sea <id>

    Examples:
      | id   | petID  | quantity | shipDate                 | status   | complete | codEstado |
      | 2    | 5      | 2        | 2025-09-19T10:27:35.372Z | placed   | true     | 200       |

  @ConsultaMascota
  Scenario Outline: Consulta de pedido en PetStore
    When envia una solicitud GET
    Then valido el codigo de respuesta es <codRespuesta>
    And valido el id con <id>

    Examples:
      | id  | codRespuesta |
      | 101 | 200          |
      | 102 | 200          |
      | 103 | 200          |