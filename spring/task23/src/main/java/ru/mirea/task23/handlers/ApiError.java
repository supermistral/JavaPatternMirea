package ru.mirea.task23.handlers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {
    private HttpStatus status;
    private List<String> errors;

    public ApiError(HttpStatus status, String error) {
        this.status = status;
        this.errors = Arrays.asList(error);
    }
}
