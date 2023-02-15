package com.webscraper.backend.service;

//import com.webscraper.backend.model.Data;
import com.webscraper.backend.model.Product;
import com.webscraper.backend.model.ProductUrl;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ProductService {




    List<Product> findAll();
     Product create( String url) throws IOException;
}
