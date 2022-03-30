package com.perficient.praxis.gildedrose.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class DuplicateItemException extends RuntimeException{

    public DuplicateItemException(String message){
        super(message);
    }
}
