package com.kcare.kcare.Product.controller.ProductController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kcare.kcare.File.FileStorageService;
import com.kcare.kcare.File.R2FileStorageService;
import com.kcare.kcare.Product.Model.Product;
import com.kcare.kcare.Product.Model.ProductAttribute;
import com.kcare.kcare.Product.Model.ProductSubpart;
import com.kcare.kcare.Product.repository.ProductAttributeRepository;
import com.kcare.kcare.Product.repository.ProductRepository;
import com.kcare.kcare.Product.repository.ProductSubpartRepository;
import com.kcare.kcare.common.PageResponse;
import com.kcare.kcare.common.Response;
import com.kcare.kcare.handler.ResourceNotFoundException;
import com.kcare.kcare.image.Model.ImageDetails;
import com.kcare.kcare.image.Model.ImageDetailsRepository;
import com.kcare.kcare.supplier.repository.SupplierRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SupplierRepository supplierRepository;
    private final ProductAttributeRepository productAttributeRepository;
    private final FileStorageService fileStorageService;
    private final ProductSubpartRepository productSubpartRepository;
    private final ImageDetailsRepository imageDetailsRepository;
    private final R2FileStorageService r2FileStorageService;

    // private final DecimalFormat df = new DecimalFormat("0.00");

    public Response<ProductRequest> createDynamicProductTable(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        Product savedProduct = productRepository.save(product);
        if (productRequest.getProductAttributes() != null) {
            List<ProductAttribute> productAttributes = productRequest.getProductAttributes();
            if (!productAttributes.isEmpty()) {
                productAttributes.forEach(p -> {
                    ProductAttribute productAttribute = new ProductAttribute();
                    productAttribute.setAttributeName(p.getAttributeName());
                    productAttribute.setAttributeValue(p.getAttributeValue());
                    productAttribute.setProduct(savedProduct);
                    productAttributeRepository.save(productAttribute);
                });
            }
        }
        return new Response<>(
                productRequest,
                LocalDateTime.now(),
                "Product detail saved successfully",
                HttpStatus.CREATED);
    }

    public Response<ProductRequest> createProduct(ProductRequest productRequest, List<MultipartFile> productImages) {
        Product product = productMapper.toProduct(productRequest);
        Product savedProduct = productRepository.save(product);
        // if (productImages != null) {
        // saveImage(productImages, savedProduct.getId());
        // }

        if (productRequest.getParentProductId() != null) {
            Product parentProduct = productRepository.findById(productRequest.getParentProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Main/Parent Does Not Exist"));
            ProductSubpart productSubpart = productMapper.toProductSubPart(savedProduct.getId(), parentProduct);
            productSubpartRepository.save(productSubpart);
        }
        return new Response<>(
                productRequest,
                LocalDateTime.now(),
                "Product detail save successfully",
                HttpStatus.CREATED);
    }

    // public void saveImage(List<MultipartFile> productImages, Integer productId) {

    // List<String> imagesPath = fileStorageService.saveProductImages(productImages,
    // productId, "productImages");
    // Product product = productRepository.findById(productId)
    // .orElseThrow(() -> new ResourceNotFoundException("Product Not Found Id: " +
    // productId));
    // for (String imagesPath2 : imagesPath) {
    // ProductImage productImage = new ProductImage();
    // productImage.setImagePath(imagesPath2);
    // productImage.setProduct(product);
    // productImageRepository.save(productImage);
    // }
    // }

    public ProductResponse getProductImageById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product Not availabe for Id :" + productId));
        // List<ProductImage> allImages =
        // productImageRepository.findAllByProductId(productId);
        // List<String> allImageUrls;
        // if (!allImages.isEmpty()) {
        // allImageUrls = allImages.stream()
        // .map(image -> {
        // Path normalizedPath = Paths.get(image.getImagePath()).normalize();
        // String relativePath = normalizedPath.toString().replace("\\", "/");
        // String baseUrl =
        // ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        // return baseUrl + "/" + relativePath;
        // })
        // .collect(Collectors.toList());
        // } else {
        // allImageUrls = List.of();
        // }
        List<ProductAttribute> productAttributes = productAttributeRepository.findAllByProductId(product.getId());
        List<ProductSubpart> productSubparts = productSubpartRepository.findAllByProductId(productId);
        List<ProductSubpartResponse> productSubpartResponses = productSubparts.stream().map(p -> {
            Product partsofProduct = productRepository.findById(p.getSubProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product Does Not Exist"));
            ProductSubpartResponse productSubpartResponse = productMapper.toProductSubpartResponse(p,
                    partsofProduct.getProductName());
            return productSubpartResponse;
        }).collect(Collectors.toList());
        ProductResponse productResponse = productMapper.toProductResponse(product,
                productSubpartResponses, productAttributes);
        return productResponse;
    }

    public PageResponse<ProductResponse> getAllProduct(String productName, int page, int size) {
        Specification<Product> spec = Specification.where(null);
        if (productName != null && !productName.isEmpty()) {
            Specification<Product> exactMatchSpec = ProductSpecification.withProductName(productName);
            Specification<Product> startsWithSpec = ProductSpecification.withProductNameStartingWith(productName);
            Specification<Product> containsSpec = ProductSpecification.withProductNameContaining(productName);
            spec = Specification.where(exactMatchSpec).or(startsWithSpec).or(containsSpec);
        }
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("productName").descending());

        Page<Product> pagedProducts = productRepository
                .findAll(spec, pageable);

        if (pagedProducts.isEmpty()) {
            throw new ResourceNotFoundException("Product Not Available in database");
        }
        List<ProductResponse> productResponse = pagedProducts.stream().map(product -> {
            // List<ProductImage> allImages =
            // productImageRepository.findAllByProductId(product.getId());
            // List<String> allImageUrls = new ArrayList<>();
            // if (!allImageUrls.isEmpty()) {
            // allImageUrls = allImages.stream()
            // .map(image -> {
            // Path normalizedPath = Paths.get(image.getImagePath()).normalize();
            // String relativePath = normalizedPath.toString().replace("\\", "/");
            // String baseUrl =
            // ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            // return baseUrl + "/" + relativePath;
            // })
            // .collect(Collectors.toList());
            // }
            List<ProductSubpart> productSubparts = productSubpartRepository.findAllByProductId(product.getId());
            List<ProductSubpartResponse> productSubpartResponses = productSubparts.stream().map(p -> {
                Product partsofProduct = productRepository.findById(p.getSubProductId())
                        .orElseThrow(() -> new EntityNotFoundException("Product Does Not Exist"));
                ProductSubpartResponse productSubpartResponse = productMapper.toProductSubpartResponse(p,
                        partsofProduct.getProductName());
                return productSubpartResponse;
            }).collect(Collectors.toList());
            List<ProductAttribute> productAttributes = productAttributeRepository.findAllByProductId(product.getId());
            ProductResponse newProductResponse = productMapper.toProductResponse(product,
                    productSubpartResponses, productAttributes);
            return newProductResponse;
        }).collect(Collectors.toList());
        return new PageResponse<>(
                productResponse,
                pagedProducts.getNumber(),
                pagedProducts.getSize(),
                pagedProducts.getTotalElements(),
                pagedProducts.getTotalPages(),
                pagedProducts.isFirst(),
                pagedProducts.isLast());

    }

    public List<String> saveProductImage(Integer productId, List<MultipartFile> files) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not available in database"));

        List<String> filesPath = files.stream()
                .map(file -> {
                    try {
                        return r2FileStorageService.uploadFile(file);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to upload file", e);
                    }
                })
                .collect(Collectors.toList());

        filesPath.forEach(filePath -> {
            ImageDetails imageDetails = new ImageDetails();
            imageDetails.setImagePath(filePath);
            imageDetails.setProduct(product);
            imageDetails.setImageTag("product");
            imageDetailsRepository.save(imageDetails);
        });

        return filesPath;

    }

    public List<String> getAllProductImage(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not available in database"));

        List<ImageDetails> imageDetails = imageDetailsRepository.findAllByProductId(productId);
        if (imageDetails.isEmpty()) {
            throw new ResourceNotFoundException("Image not available");
        }

        List<String> productImageUrl = imageDetails.stream().map(imageDetail -> {
            String productImagePathUrl = r2FileStorageService.getImageUrl(imageDetail.getImagePath());
            return productImagePathUrl;
        }).collect(Collectors.toList());
        return productImageUrl;

    }

    // public Response<String> ge

    // public Response<ProductRequest> createDynamicProductTable(ProductRequest
    // productRequest) {

    // Product product = productMapper.toProduct(productRequest);
    // Product savedProduct = productRepository.save(product);
    // Supplier supplier = productMapper.toSupplier(productRequest, savedProduct);
    // supplierRepository.save(supplier);
    // if (productRequest.getProductAttributes() == null) {
    // log.info("koi extra row create kiya hi nhi tum");
    // }
    // if (productRequest.getProductAttributes() != null) {
    // log.info("tum check kro code start hua ki nhi");
    // List<ProductAttribute> productAttributes =
    // productRequest.getProductAttributes();
    // if (!productAttributes.isEmpty()) {
    // productAttributes.forEach(p -> {
    // ProductAttribute productAttribute = new ProductAttribute();
    // productAttribute.setAttributeName(p.getAttributeName());
    // productAttribute.setAttributeValue(p.getAttributeValue());
    // productAttribute.setProduct(savedProduct);
    // productAttributeRepository.save(productAttribute);
    // });
    // }

    // }
    // return new Response<>(
    // productRequest,
    // LocalDateTime.now(),
    // "Product detail save successfully",
    // HttpStatus.CREATED);
    // }

}
