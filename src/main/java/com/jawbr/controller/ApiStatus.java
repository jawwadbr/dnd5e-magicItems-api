package com.jawbr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiStatus {

    @RequestMapping(value = "/api/status", method = RequestMethod.HEAD)
    public HttpStatus checkApiStatus() {
        return HttpStatus.OK;
    }
}
