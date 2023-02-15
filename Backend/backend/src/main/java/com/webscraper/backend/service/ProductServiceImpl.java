package com.webscraper.backend.service;

//import com.webscraper.backend.model.Data;
import com.webscraper.backend.model.Product;
import com.webscraper.backend.model.ProductUrl;
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


//        String url = "https://www.amazon.in/Sony-WH-G900N-Cancelling-Compatible-transactions/dp/B0B1TDHLPT/ref=sr_1_4?crid=1H2XTCT5XY04C&keywords=sony%2Bnew%2Bheadset&qid=1676216613&s=digital-text&sprefix=sony%2Bnew%2Bhead%2Bset%2Cdigital-text%2C211&sr=1-4&th=1";


        try{
            Document doc = Jsoup.connect(url).get();
            Elements productTitle = doc.select("#productTitle");
            newProduct.setName(productTitle.text());

            Elements reviews = doc.select(".a-section.review");
//            System.out.println(reviews);



		for (Element review : reviews) {
            System.out.println(review);

			String rating = review.select(".a-icon-alt").text();
			String title = review.select(".review-title").text();
			String content = review.select(".review-text").text();
            newReviews.setTitle(title);
            newReviews.setRating(rating);
            newReviews.setContext(content);
//            System.out.println(title);
//            System.out.println(rating);
//            System.out.println(content);
//            reviewRepository.

            reviewRepository.save(newReviews);

		}

        } catch (IIOException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("posted");
        return productRepository.save(newProduct);
    }


//
//    @Override
//    public List<Data> getAll() {
//        return  dataRepository.findAll();
//    }
//
//    @Override
//    public Data create(Data data) {
//        Data newData = new Data();
//
//        try{
//
//            Document document = Jsoup.connect(data.getUrl()).timeout(5000).get();
//            newData.setTitle(document.title());
//            newData.setId(data.getId());
//            newData.setUrl(data.getUrl());
//
//        } catch (IIOException e){
//            e.printStackTrace();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        return  dataRepository.save(newData);
//
//    }
}
