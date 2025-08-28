package com.staj.CarCommerceApp.controllers;


import com.staj.CarCommerceApp.models.Car;
import com.staj.CarCommerceApp.models.Poll;
import com.staj.CarCommerceApp.services.PollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    private final PollService pollService;
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping
    public Poll pollCreate(@RequestBody Poll poll) {
        return pollService.createPoll(poll);
    }

}
