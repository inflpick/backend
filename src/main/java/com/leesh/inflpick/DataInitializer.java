package com.leesh.inflpick;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leesh.inflpick.influencer.adapter.out.persistence.mongo.InfluencerDocument;
import com.leesh.inflpick.influencer.adapter.out.persistence.mongo.InfluencerMongoRepository;
import com.leesh.inflpick.keyword.adapter.out.persistence.mongo.KeywordDocument;
import com.leesh.inflpick.keyword.adapter.out.persistence.mongo.KeywordMongoRepository;
import com.leesh.inflpick.product.adapter.out.persistence.mongo.ProductDocument;
import com.leesh.inflpick.product.adapter.out.persistence.mongo.ProductMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Profile("!prod")
@RequiredArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    private final InfluencerMongoRepository influencerMongoRepository;
    private final KeywordMongoRepository keywordMongoRepository;
    private final ProductMongoRepository productMongoRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {

        influencerMongoRepository.deleteAll();

        InputStream inputStream = new ClassPathResource("/data/influencers.json").getInputStream();
        List<InfluencerDocument> influencers = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, InfluencerDocument.class));
        influencerMongoRepository.saveAll(influencers);

        keywordMongoRepository.deleteAll();

        inputStream = new ClassPathResource("/data/keywords.json").getInputStream();
        List<KeywordDocument> keywords = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, KeywordDocument.class));
        keywordMongoRepository.saveAll(keywords);

        productMongoRepository.deleteAll();

        inputStream = new ClassPathResource("/data/products.json").getInputStream();
        List<ProductDocument> products = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, ProductDocument.class));
        productMongoRepository.saveAll(products);
    }
}
