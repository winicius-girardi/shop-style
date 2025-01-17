package br.com.mscustomer.controller.response;

import lombok.Builder;

@Builder
public record ErrorField(String field,
                         String message) {
}
