package br.com.ms_catalog.exception

import br.com.ms_catalog.controller.response.ErrorField

class ValidationException (val errors: List<ErrorField>): RuntimeException() {

}