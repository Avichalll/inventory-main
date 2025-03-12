package com.kcare.kcare.organisation.CompanyOwnerController;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/companyOwner")
@RequiredArgsConstructor

public class CompanyOwnerController {

    private final CompanyOwnerService hospitalOwnerService;

    @PostMapping(value = "/saveDetail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCompanyOwnerDetail(@RequestBody CompanyOwnerRequest companyOwnerRequest) {
        return ResponseEntity.ok(hospitalOwnerService.saveCompanyOwnerDetail(companyOwnerRequest));
    }

}
