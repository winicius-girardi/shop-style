package br.com.mscustomer.enums;

public enum Gender {
    MALE("Masculino"), FEMALE("Feminino"),NOT_SPECIFIED("Não especificado"),OTHER("Outro");

    private String value;

    Gender(String value) {
        this.value = value;
    }


}
