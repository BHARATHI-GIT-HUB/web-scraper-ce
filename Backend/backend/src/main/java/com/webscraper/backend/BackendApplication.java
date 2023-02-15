package com.webscraper.backend;

import com.webscraper.backend.model.Product;
import com.webscraper.backend.repository.ProductRepository;
import com.webscraper.backend.repository.ReviewRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(BackendApplication.class, args);
//		System.out.println("hello");



//		Elements reviews = doc.select(".a-section.review");
//
//		for (Element review : reviews) {
////			System.out.println(review);
////			System.out.println(review.select("a-profile-name").text());
//			String rating = review.select(".a-icon-alt").text();
//			String title = review.select(".review-title").text();
//			String content = review.select(".review-text").text();
//			System.out.println("Rating: " + rating);
//			System.out.println("Title: " + title);
//			System.out.println("Content: " + content);
////			System.out.println("-----------------------------------");
//		}

	}

}
