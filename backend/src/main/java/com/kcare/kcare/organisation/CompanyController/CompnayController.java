package com.kcare.kcare.organisation.CompanyController;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/company")
@RequiredArgsConstructor
public class CompnayController {

    private final CompanyService companyService;

    @PostMapping(value = "/saveDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCompanyDetails(@RequestBody CompanyRequest companyRequest) {
        return ResponseEntity.ok(companyService.saveCompanyDetails(companyRequest));

    }

}
