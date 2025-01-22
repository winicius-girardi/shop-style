package br.com.mscustomer.controller.response;

import lombok.Builder;

@Builder
public record MessageResponse(String  message) {
}
