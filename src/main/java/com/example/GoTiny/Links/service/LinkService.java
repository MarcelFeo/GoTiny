package com.example.GoTiny.Links.service;

import com.example.GoTiny.Links.model.Link;
import com.example.GoTiny.Links.repository.LinkRepository;
import org.springframework.stereotype.Service;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;

@Service
public class LinkService {

    private LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public String generateRandomURL() {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }

    public Link shortenURL(String originalUrl) {
        Link link = new Link();

        link.setUrlLong(originalUrl);
        link.setUrlShort(generateRandomURL());
        link.setUrlCreated_at(LocalDateTime.now());
        link.setUrlQrCode("TODO: create the QR code logic");

        return linkRepository.save(link);
    }

    public Link getOriginalUrl(String shortUrl) {

        try {
            return linkRepository.findByUrlLong(shortUrl);
        } catch(Exception e) {
            throw new RuntimeException("URL not exist...", e);
        }
    }

}
