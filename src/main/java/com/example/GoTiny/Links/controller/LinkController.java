package com.example.GoTiny.Links.controller;

import com.example.GoTiny.Links.model.LinkResponse;
import com.example.GoTiny.Links.model.Link;
import com.example.GoTiny.Links.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LinkController {

    private LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/gotiny")
    public ResponseEntity<LinkResponse> generateShortUrl(@RequestBody Map<String, String> request) {

        String originalUrl = request.get("originalUrl");
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

    }

}
