package com.webscraper.backend.service;


import com.webscraper.backend.model.Product;
import com.webscraper.backend.model.Review;
import com.webscraper.backend.repository.ProductRepository;
import com.webscraper.backend.repository.ReviewRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product create(String url) throws IOException {
        Product newProduct  = new Product();
        Review newReviews = new Review();
        List<Review> saveReviews = new ArrayList<>();
        Product res = null;



//        String url = "https://www.amazon.in/Sony-WH-G900N-Cancelling-Compatible-transactions/dp/B0B1TDHLPT/ref=sr_1_4?crid=1H2XTCT5XY04C&keywords=sony%2Bnew%2Bheadset&qid=1676216613&s=digital-text&sprefix=sony%2Bnew%2Bhead%2Bset%2Cdigital-text%2C211&sr=1-4&th=1";


        try{
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36")
                    .maxBodySize(0)
                    .get();
            Elements productTitle = doc.select("#productTitle");
            newProduct.setName(productTitle.text());

            Elements reviews = doc.select(".a-section.review");



		for (Element review : reviews) {

			String rating = review.select(".a-icon-alt").text();
			String title = review.select(".review-title").text();
			String content = review.select(".review-text").text();
            newReviews.setId(newProduct.getId());
            newReviews.setTitle(title);
            newReviews.setRating(rating);
            newReviews.setContext(content);

            saveReviews.add(reviewRepository.save( newReviews ));
		}
            System.out.println(saveReviews);

        } catch (IIOException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newProduct.setReview(saveReviews);

        return productRepository.save(newProduct);


    }

}
