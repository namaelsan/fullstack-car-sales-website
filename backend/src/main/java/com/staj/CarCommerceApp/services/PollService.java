package com.staj.CarCommerceApp.services;

import com.staj.CarCommerceApp.models.Poll;
import com.staj.CarCommerceApp.repositories.PollRepository;
import org.springframework.stereotype.Service;

@Service
public class PollService {

    private final PollRepository pollRepository;
    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Poll createPoll(Poll poll) {
        poll.setId(null);
        return pollRepository.save(poll);
    }
}
