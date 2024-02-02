package com.example.PetPalHub.Security.config;

import lombok.Data;

@Data
public class CommonConfigs {
    static final String[] AUTH_WHITELIST = {
            "/proxy/**",
            "/adopter/dashboard/**",
            "/master/updatePetProfilePicture/**",
            "/adopter/petForUser/**",
            "/user/updateUserProfilePicture/**",
    };
  
    static final String[] AUTH_AUTHENTICATED = {
            "/master/getShelterDto/**",
            "/user/getUserDto/**",
            "/user/updateUserProfile/**",
    };
    static final String[] AUTH_ADOPTER= {
            "/adopter/applyForPet/**",
            "/adopter/getApplications/**",
    };
    static final String[] AUTH_MASTER= {
            "/master/acceptApplication/**",
            "/master/rejectApplication/**",
            "/master/getPendingApplicationByShelterID/**",
            "/master/getAcceptedApplicationByShelterID/**",
            "/master/getRejectedApplicationByShelterID/**",
            "/master/getFilteredPetsHeadersListByShelterId/**",
            "/master/addPet/**",
            "/master/editPet/**",
            "/master/updateShelterDto/**",
    };
}
