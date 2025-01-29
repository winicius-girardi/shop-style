package br.com.mscustomer.exception.response;

import lombok.Builder;

@Builder
public record ErrorField(String field,
                         String message) {
}
