package br.com.mscustomer.exception.response;

import lombok.Builder;

@Builder
public record MessageResponse(String  message) {
}
