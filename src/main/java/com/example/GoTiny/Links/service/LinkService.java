package com.example.GoTiny.Links.service;

import com.example.GoTiny.Links.model.Link;
import com.example.GoTiny.Links.repository.LinkRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Base64;

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
        String shortCode = generateRandomURL();
        String shortUrl = "http://localhost:8081/" + shortCode;

        link.setUrlLong(originalUrl);
        link.setUrlShort(shortCode);
        link.setUrlCreated_at(LocalDateTime.now());

        try {
            byte[] qr = generateQRCode(shortUrl);
            String base64 = Base64.getEncoder().encodeToString(qr);
            link.setUrlQrCode(base64);
        } catch (Exception e) {
            throw new RuntimeException("Error: QR Code", e);
        }

        return linkRepository.save(link);
    }

    public Link getOriginalUrl(String shortUrl) {

        try {
            return linkRepository.findByUrlShort(shortUrl);
        } catch(Exception e) {
            throw new RuntimeException("URL not exist...", e);
        }
    }

    public byte[] generateQRCode(String url) throws Exception {
        int width = 300;
        int height = 300;

        BitMatrix bitMatrix = new MultiFormatWriter()
                .encode(url, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

        return outputStream.toByteArray();
    }

}
