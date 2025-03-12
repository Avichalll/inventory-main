package com.kcare.kcare.organisation.CompanyController;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kcare.kcare.common.Response;
import com.kcare.kcare.organisation.Model.Company;
import com.kcare.kcare.organisation.Model.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CompanyService {

    private final CompanyRepository hospitalRepository;
    private final CompanyMapper hospitalMapper;

    public Response<CompanyRequest> saveCompanyDetails(CompanyRequest companyRequest) {

        Company hospital = hospitalMapper.toHospital(companyRequest);
        hospitalRepository.save(hospital);

        return new Response<>(
                companyRequest,
                LocalDateTime.now(),
                "detail saved",
                HttpStatus.CREATED

        );

    }

}
