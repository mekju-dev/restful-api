package com.test.demo;

import org.springframework.http.ResponseEntity;

public interface Command<I,O> {
    ResponseEntity<O> execute(I input);
}
