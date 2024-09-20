package com.gabriel.taskmanagerapi.service;

import com.gabriel.taskmanagerapi.domain.Activity;
import com.gabriel.taskmanagerapi.dto.activity.ActivityDetailsDTO;
import com.gabriel.taskmanagerapi.dto.activity.ActivityRegisterDTO;
import com.gabriel.taskmanagerapi.dto.activity.ActivityUpdateDTO;
import com.gabriel.taskmanagerapi.exception.handler.ActivityUpdateDescriptionNullException;
import com.gabriel.taskmanagerapi.repository.ActivityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    private static final String PREFIX = "There are no activities for this id: ";

    public ActivityDetailsDTO findById(Long id) {
        if(!activityRepository.existsById(id)){
            throw new EntityNotFoundException(PREFIX + id);
        }
        return new ActivityDetailsDTO(activityRepository.getReferenceById(id));
    }

    public Page<ActivityDetailsDTO> findAll(Pageable pageable) {
        return activityRepository.findAll(pageable).map(ActivityDetailsDTO::new);
    }

    public ActivityDetailsDTO register(ActivityRegisterDTO activityRegisterDTO) {
        return new ActivityDetailsDTO(
                activityRepository.save(convertActivityDetailsDTOToActivity(activityRegisterDTO)));
    }

    public ActivityDetailsDTO update(Long id, ActivityUpdateDTO activityUpdateDTO) {
        if(!activityRepository.existsById(id)){
            throw new EntityNotFoundException(PREFIX + id);
        }
        if(activityUpdateDTO.description() == null){
            throw new ActivityUpdateDescriptionNullException("The updated description cannot be null");
        }
        Activity activity = activityRepository.getReferenceById(id);
        activity.setDescription(activityUpdateDTO.description());
        return new ActivityDetailsDTO(activityRepository.save(activity));
    }

    public void deleteById(Long id) {
        if(!activityRepository.existsById(id)){
            throw new EntityNotFoundException(PREFIX + id);
        }
        activityRepository.deleteById(id);
    }

    public Activity convertActivityDetailsDTOToActivity(ActivityRegisterDTO activityRegisterDTO) {
        Activity activity = new Activity();
        activity.setDescription(activityRegisterDTO.description());
        return activity;
    }
}
