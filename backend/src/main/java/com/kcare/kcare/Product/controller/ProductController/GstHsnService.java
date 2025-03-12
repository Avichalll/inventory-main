package com.kcare.kcare.Product.controller.ProductController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// @Service
@RequiredArgsConstructor
@Slf4j

@RestController
@RequestMapping("/hsn")
public class GstHsnService {

    private final OkHttpClient okHttpClient;
    private final String baseUrl = "https://api.sandbox.co.in";

    @Value("${sandbox.api.key}")
    private String apiKey;
    @Value("${sandbox.eway.bill.token}")
    private String ewayBillToken;
    @Value("${sandbox.api.version}")
    private String apiVersion;

    public Response getHsnDetail(String hsnCode) throws IOException {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + "/gst/compliance/e-way-bill/tax-payer/hsn")
                .newBuilder()
                .addQueryParameter("hsn_code", hsnCode);

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .addHeader("accept", "application/json")
                .addHeader("authorization", ewayBillToken)
                .addHeader("x-api-key", apiKey)
                .addHeader("x-api-version", apiVersion)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            log.error("API call failed with code: {}, body: {}",
                    response.code(),
                    response.body() != null ? response.body().string() : "null");
            throw new IOException("Unexpected response code: " + response);
        } catch (IOException e) {
            log.error("Error fetching HSN details for code: {}", hsnCode, e);
            throw e;
        }

    }

    // @GetMapping("/getHsnDetails/{hsn_code}")
    // public ResponseEntity<?> getHsnDetails(@PathVariable("hsn_code") String
    // hsn_code) throws IOException {

    // // Response res = getHsnDetail(hsn_code);

    // return ResponseEntity.ok(getHsnDetail(hsn_code));

    // }
}
