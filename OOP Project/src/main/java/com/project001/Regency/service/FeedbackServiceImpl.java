package com.project001.Regency.service;

import com.project001.Regency.model.Feedback;
import com.project001.Regency.repository.FeedbackRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl {

    private final FeedbackRepo feedbackRepo;

    public FeedbackServiceImpl(FeedbackRepo feedbackRepo) {
        this.feedbackRepo = feedbackRepo;
    }

    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepo.save(feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepo.findAll();
    }

    public void deleteFeedback(Long id) {
        feedbackRepo.deleteById(id);
    }
}
