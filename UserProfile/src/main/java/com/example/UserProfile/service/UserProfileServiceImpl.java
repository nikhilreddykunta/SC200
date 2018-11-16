package com.example.UserProfile.service;

import com.example.UserProfile.domain.UserProfile;
import com.example.UserProfile.exception.UserProfileAlreadyExitsException;
import com.example.UserProfile.exception.UserProfileNotFoundException;
import com.example.UserProfile.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service

public class UserProfileServiceImpl implements UserProfileService {




    private UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository){
        this.userProfileRepository = userProfileRepository;
    }


    @Override
    public List<UserProfile> getAllUserProfiles() {
        List<UserProfile> topFive = new ArrayList<UserProfile>();
        List<UserProfile> allElement=new ArrayList<UserProfile>();
       allElement=userProfileRepository.findAll();
//        for(int i=0;i<3;i++){
//            topFive.add(allElement.get(i));
//        }
        return allElement;
    }
    @Override
    public UserProfile saveUserProfile(UserProfile userProfile) throws UserProfileAlreadyExitsException {
        if(userProfileRepository.existsById(userProfile.getEmail())){
           // logger.info("exception ocuured");
            throw new UserProfileAlreadyExitsException("UserProfile already Exists");
        }


        UserProfile saveuserProfile= userProfileRepository.save(userProfile);

        return saveuserProfile;
    }
    @Override
    public UserProfile searchUserProfileById(String id) throws UserProfileNotFoundException {
       // Voting voting= votingRepository.findById(id).get();
        if(userProfileRepository.existsById(id)) {
            UserProfile userProfile = userProfileRepository.findById(id).get();
            return userProfile;
        }
        else{
            throw new UserProfileNotFoundException("UserProfile not found");
        }
    }
    @Override
      public boolean deleteUserProfilebyId(String id) {

       if (userProfileRepository.existsById(id)) {

           userProfileRepository.deleteById(id);
           return true;
       }
       else{
           return false;
       }
    }

    @Override
    public UserProfile  updateuserProfileById(String id,UserProfile userProfile1) throws UserProfileNotFoundException{
       // Movie movie=movieRepository.findById(id).get();
        if(userProfileRepository.existsById(id)) {
            UserProfile userProfile = userProfileRepository.findById(id).get();
            userProfile.setChallengeAttempted(userProfile1.getChallengeAttempted());
            userProfile.setFirstName(userProfile1.getFirstName());
            userProfile.setLastName(userProfile1.getLastName());
            userProfile.setContactNumber(userProfile1.getContactNumber());
            userProfile.setUsername(userProfile1.getUsername());
            userProfile.setRanking(userProfile1.getRanking());
            userProfile.setScore(userProfile1.getScore());
            userProfile.setChallengeCreated(userProfile1.getChallengeCreated());
            userProfile.setChallengeDownvoted(userProfile1.getChallengeDownvoted());
            userProfile.setChallengeUpvoted(userProfile1.getChallengeUpvoted());
            userProfile.setDateOfBirth(userProfile1.getDateOfBirth());
            userProfile.setPreferredLang(userProfile1.getPreferredLang());


            //Movie movie2=movieRepository.save(movie);
            UserProfile userProfile2 = userProfileRepository.save(userProfile);
            return userProfile2;
        }
        else{
            throw new UserProfileNotFoundException("UserProfile not found");
        }
    }



}
