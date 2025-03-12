package com.kcare.kcare.Product.controller.ProductController;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kcare.kcare.common.PageResponse;
import com.kcare.kcare.common.Response;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/product")
@RequiredArgsConstructor

public class ProductController {

    private final ProductService newProductService;

    @PostMapping(value = "/createDynamicTable", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<ProductRequest>> createDynamicProductTable(
            @RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(newProductService.createDynamicProductTable(productRequest));
    }

    @PostMapping(path = "/saveProduct", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<ProductRequest>> createProduct(@RequestBody ProductRequest productRequest,
            @RequestPart(name = "file", required = false) List<MultipartFile> file) {
        return ResponseEntity.ok(newProductService.createProduct(productRequest, file));
    }

    @GetMapping(path = "/getProduct/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> getProductImageById(@PathVariable("productId") Integer productId) {
        ProductResponse productResponse = newProductService.getProductImageById(productId);
        return ResponseEntity.ok()
                .body(productResponse);
    }

    @GetMapping(path = "/getAllProduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<ProductResponse>> getAllProduct(
            @RequestParam(name = "productName", required = false) String productName,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "6", required = false) int size) {
        return ResponseEntity.ok(newProductService.getAllProduct(productName, page,
                size));
    }

    // @PostMapping(path = "/saveProduct", consumes = { "application/json",
    // "multipart/form-data" })
    // public ResponseEntity<Response<ProductRequest>> createProduct(@RequestBody
    // ProductRequest productRequest,
    // @RequestPart(name = "file", required = false) List<MultipartFile> file) {
    // return ResponseEntity.ok(productService.createProduct(productRequest, file));
    // }

    // @GetMapping("/searchProduct")
    // public ResponseEntity<PageResponse<ProductResponse>> searchProduct(

    // )

    @PostMapping(path = "saveProductImage/{productId}", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> saveProductImage(@PathVariable("productId") Integer productId,
            @RequestPart(name = "files", required = true) List<MultipartFile> files) {
        return ResponseEntity.ok(newProductService.saveProductImage(productId, files));
    }

    @GetMapping(path = "getProductImage/{productId}")
    public ResponseEntity<?> getAllProductImage(@PathVariable("productId") Integer productId) {
        return ResponseEntity.ok(newProductService.getAllProductImage(productId));

    }

}
