package br.com.mscustomer.factory;

public class Factory {

    public final static String VALID_ADDRESS=
            """
                {
                    "state":"SC",
                    "city":"Paial",
                    "district":"Teste",
                    "street": "Teste",
                    "number": 123,
                    "cep": "12345-789",
                    "complement":"Teste",
                    "customerId": 1
                }
            """;

    public final static String INVALID_ADDRESS=
            """
                {
                    "state":"",
                    "city":"",
                    "district":"",
                    "street": "",
                    "number": 0,
                    "cep": "",
                    "complement":"",
                    "customerId": 0
                }
            """;

    public final static String INVALID_ADDRESS_RESPONSE=
            """
                [
                    {
                        "field":"ADDRESS",
                        "message":"CEP PRECISA SER NO FORMATO XXXXX-XXX"
                    },
                    {
                        "field":"CITY",
                        "message":"CIDADE PRECISA SER INFORMADA"
                    },
                    {
                        "field":"STATE",
                        "message":"ESTADO PRECISA SER INFOMARDO"
                    },
                    {
                        "field":"NUMBER",
                        "message":"CAMPO NUMERO SÓ PODE CONTER NUMEROS"
                    },
                    {
                        "field":"DISTRICT",
                        "message":"DISTRITO PRECISA SER INFORMADO"
                    },
                    {
                        "field":"STREET",
                        "message":"RUA PRECISA SER INFORMADA"
                    },
                    {
                        "field":"STATE",
                        "message":"ESTADO INFORMADO É INVÁLIDO"
                    }
                ]
            """.trim();

public final static String VALID_ADDRESS_CHANGE=
        """
                {
                    "state": "RS",
                    "city": "teste",
                    "street": "teste",
                    "district": "teste",
                    "number": 5410,
                    "cep": "88888-888",
                    "complement": "teste"
                }
        """;


}
