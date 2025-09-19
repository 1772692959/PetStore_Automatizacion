@CrearValidar
  Feature: Validacion de API Store de PetStore
    COMO automatizador de NTTDATA
    QUIERO validar la integridad de creacion y consulta de pedidos en la tienda PetStore
    PARA garantizar el funcionamiento y validación de la conexion de la BD

    Background:
      Given defino la URL base de la api "https://petstore.swagger.io/v2/"

    @TestCreacionOrder
    Scenario Outline: Creacion de un nuevo pedido en PetStore
      Given creacion de Order POST "/store/order"
      When envia una peticion con el body:
        """
        {
          "id": <id>,
          "petId": <petId>,
          "quantity": <quantity>,
          "shipDate": "<shipDate>",
          "status": "<status>",
          "complete": <complete>
        }
        """
      Then valido el codigo de respuesta sea <codEstado>
      And valido que el body contenga:
        | id       | <id>       |
        | petId    | <petId>    |
        | quantity | <quantity> |
        | status   | <status>   |
        | complete | <complete> |

      Examples:
        | id  | petId | quantity | shipDate                 | status | complete | codEstado |
        | 2   | 5     | 2        | 2025-09-19T10:27:35.372Z | placed | true     | 200       |

    @TestConsultaOrder
    Scenario Outline: Consulta de un pedido existente en PetStore
      Given consulta de Order GET "<id>"
      When se realiza la peticion para obtener el pedido
      Then valido el codigo de respuesta sea <codEstado>
      And valido que el body contenga:
        | id       | <id>       |

      Examples:
        | id  | codEstado |
        | 2   | 200       |
