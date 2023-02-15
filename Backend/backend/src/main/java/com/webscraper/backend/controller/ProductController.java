package com.webscraper.backend.controller;



import com.webscraper.backend.model.Product;
import com.webscraper.backend.model.ProductUrl;
import com.webscraper.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:5500")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getDatas() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Product> postUrl(@RequestBody ProductUrl productUrl) throws IOException {
        String url = productUrl.getUrl();
        return new ResponseEntity<>(productService.create(url), HttpStatus.CREATED);
    }

}
