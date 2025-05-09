package dev.arun.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
//@Getter
//@Setter
public class ExceptionDto {
    private HttpStatus errorCode;
    private String message;

    public ExceptionDto(HttpStatus status, String message){
        this.errorCode = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
