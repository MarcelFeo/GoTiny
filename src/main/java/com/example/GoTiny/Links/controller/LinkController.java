package com.example.GoTiny.Links.controller;

import com.example.GoTiny.Links.model.LinkResponse;
import com.example.GoTiny.Links.model.Link;
import com.example.GoTiny.Links.service.LinkService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class LinkController {

    private LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/gotiny")
    @CrossOrigin(origins = "*")
    public ResponseEntity<LinkResponse> generateShortUrl(@RequestBody Map<String, String> request) {

        try {
            String originalUrl = request.get("urlLong");
            Link link = linkService.shortenURL(originalUrl);

            String redirectUserUrl = "http://localhost:8081/r/" + link.getUrlShort();

            LinkResponse response = new LinkResponse(
                    link.getId(),
                    link.getUrlLong(),
                    redirectUserUrl,
                    link.getUrlQrCode(),
                    link.getUrlCreated_at()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            throw new RuntimeException("URL not exist...", e);
        }

    }

    @GetMapping("/r/{urlShort}")
    @CrossOrigin(origins = "*")
    public void redirectLink(@PathVariable String urlShort, HttpServletResponse response) {

        Link link = linkService.getOriginalUrl(urlShort);

        try {
            response.sendRedirect(link.getUrlLong());
        } catch(Exception e) {
            throw new RuntimeException("URL not exist...", e);
        }

    }
}
