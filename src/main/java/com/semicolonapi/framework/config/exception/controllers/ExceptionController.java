package com.semicolonapi.framework.config.exception.controllers;


import com.semicolonapi.framework.config.exception.CAccessDeniedException;
import com.semicolonapi.framework.config.exception.CEntryPointException;
import com.semicolonapi.framework.config.exception.CUnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExceptionController {
    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @GetMapping(value = "/exception/entrypoint")
    public void entryPointException() {
        throw new CEntryPointException();
    }

    @GetMapping(value = "/exception/accessdenied")
    public void accessdeniedException() {
        throw new CAccessDeniedException();
    }

    @GetMapping(value = "/exception/unauthorized")
    public void unauthorizedException() {
        throw new CUnAuthorizedException();
    }

}
