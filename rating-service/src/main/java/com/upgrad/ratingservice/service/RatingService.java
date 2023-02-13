package com.upgrad.ratingservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.upgrad.ratingservice.exception.RatingValueInvalidException;
import com.upgrad.ratingservice.model.DoctorRating;
import com.upgrad.ratingservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private MessageService messageService;
    //method to rate Kafka and calculate the average rating based on number of users that have rated the Doctor
    public DoctorRating rateDoctor(DoctorRating doctorRating) throws IOException {
        DoctorRating savedRating = null;
        if(doctorRating.getRating()>0 && doctorRating.getRating()<=5) {
            DoctorRating rating = this.ratingRepository.findByDoctorId(doctorRating.getDoctorId());
            if (null != rating) {
                Integer numberOfPeopleRated = rating.getNumberOfPeopleRated();
                Integer newRating = (rating.getTotalRating()) / (numberOfPeopleRated + 1);
                rating.setRating(newRating);
                rating.setTotalRating(rating.getTotalRating()+ doctorRating.getRating());
                rating.setNumberOfPeopleRated(numberOfPeopleRated+1);
                savedRating = this.ratingRepository.save(rating);
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JSR310Module());
                this.messageService.produceMessage("DOCTOR_RATING", doctorRating.getDoctorId(), mapper.writeValueAsString(rating));
            } else {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JSR310Module());
                doctorRating.setNumberOfPeopleRated(1);
                savedRating = this.ratingRepository.save(doctorRating);
                this.messageService.produceMessage("DOCTOR_RATING", doctorRating.getDoctorId(), mapper.writeValueAsString(doctorRating));
            }
        }else{
            throw new RatingValueInvalidException();
        }
return savedRating;
    }
}
