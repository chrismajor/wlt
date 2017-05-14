package io.chrismajor.wlt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to return a HTTP 500 error via the UI
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ItBrokeException extends RuntimeException {
}
