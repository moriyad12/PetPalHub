package com.example.PetPalHub.Security.Service;


import com.example.PetPalHub.Dto.UserDto;
import com.example.PetPalHub.Entities.Shelter.Location;
import com.example.PetPalHub.Entities.Shelter.Shelter;
import com.example.PetPalHub.Entities.users.Adopter;
import com.example.PetPalHub.Entities.users.Manager;
import com.example.PetPalHub.Entities.users.Staff;
import com.example.PetPalHub.Entities.users.User;
import com.example.PetPalHub.Exceptions.Securiity.ExistingMail;
import com.example.PetPalHub.Exceptions.Securiity.ForbiddenException;
import com.example.PetPalHub.Exceptions.Shelter.ShelterAlreadyCreatedException;
import com.example.PetPalHub.Exceptions.Shelter.ShelterNotFoundException;
import com.example.PetPalHub.Exceptions.UsersExceptions.UserNotFoundException;
import com.example.PetPalHub.MailSender.EmailSenderService;
import com.example.PetPalHub.RepositoriesService.Shelter.ShelterRepositoryService;
import com.example.PetPalHub.RepositoriesService.users.AdopterRepositoryService;
import com.example.PetPalHub.RepositoriesService.users.ManagerRepositoryService;
import com.example.PetPalHub.RepositoriesService.users.StaffRepositoryService;
import com.example.PetPalHub.RepositoriesService.users.UserRepositoryService;
import com.example.PetPalHub.Security.authenticationMessages.AuthenticationRequest;
import com.example.PetPalHub.Security.authenticationMessages.AuthenticationResponse;
import com.example.PetPalHub.Security.authenticationMessages.VerifyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class ProxyService {

    private final UserRepositoryService userRepositoryService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailSenderService emailSenderService;
    private final ShelterRepositoryService shelterRepositoryService;
    private final ManagerRepositoryService managerRepositoryService;
    private final StaffRepositoryService staffRepositoryService;
    private final AdopterRepositoryService adopterRepositoryService;

    public boolean mailInSystem(String email) {
        try {
            userRepositoryService.findByEmail(email);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        }
    }

    public void removeIfNotEnabled(String email) {
        if (userRepositoryService.existsByEmail(email)) {
            User user = userRepositoryService.findByEmail(email);
            if (!user.isEnabled()) {
                userRepositoryService.deleteById(user.getId());
            }
        }
    }

    public void handleException(String mail) {
        if (mailInSystem(mail)) {
            throw new ExistingMail();
        }
    }

    private String createCode() {
        StringBuilder code = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            char c = (char) (r.nextInt(9) + '0');
            code.append(c);
        }
        return code.toString();
    }

    String generateTokenForSignUp(User user, String verifyCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("verifyCode", verifyCode);
        map.put("id", user.getId());
        return jwtService.generateToken(map, user);
    }

    String generateTokenForSignIn(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        return jwtService.generateToken(map, user);
    }

    public User createUser(UserDto registerRequest) {
        return User.builder()
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role((registerRequest.getRole()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .gender((registerRequest.getGender()))
                .signInWithEmail(registerRequest.isSignInWithEmail())
                .build();
    }

    public Manager createManager(UserDto registerRequest) {
        return Manager.builder()
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role((registerRequest.getRole()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .gender((registerRequest.getGender()))
                .signInWithEmail(registerRequest.isSignInWithEmail())
                .phoneNumber(registerRequest.getPhoneNumber())
                .build();
    }
    public Staff createStaff(UserDto registerRequest) {
        return Staff.builder()
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role((registerRequest.getRole()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .gender((registerRequest.getGender()))
                .signInWithEmail(registerRequest.isSignInWithEmail())
                .phoneNumber(registerRequest.getPhoneNumber())
                .build();
    }
    public Adopter createAdopter(UserDto registerRequest) {
        return Adopter.builder()
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role((registerRequest.getRole()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .gender((registerRequest.getGender()))
                .signInWithEmail(registerRequest.isSignInWithEmail())
                .phoneNumber(registerRequest.getPhoneNumber())
                .build();
    }
    private Manager saveManager(UserDto registerRequest) {
        if (shelterRepositoryService.existByCode(registerRequest.getShelterCode())) {
            throw new ShelterAlreadyCreatedException(registerRequest.getShelterCode());
        }
        Shelter shelter = Shelter.builder().name("New Shelter").code(registerRequest.getShelterCode()).build();
        Manager manager = createManager(registerRequest);
        Location location= Location.builder()
                .country("")
                .city("")
                .address("")
                .build();
        shelter.setShelterLocation(location);
        manager.setShelter(shelter);
        managerRepositoryService.add(manager);
        return manager;
    }

    private Staff saveStaff(UserDto registerRequest) {
        if (!shelterRepositoryService.existByCode(registerRequest.getShelterCode())) {
            throw new ShelterNotFoundException(registerRequest.getShelterCode());
        }
        Shelter shelter = shelterRepositoryService.findByCode(registerRequest.getShelterCode());
        if (shelter.getId() != registerRequest.getShelterId()) {
            throw new ShelterNotFoundException(registerRequest.getShelterId());
        }
        Staff staff = createStaff(registerRequest);
        staff.setShelter(shelter);
        staffRepositoryService.add(staff);
        return staff;
    }

    private Adopter saveAdopter(UserDto registerRequest) {
        Adopter adopter = createAdopter(registerRequest);
        adopterRepositoryService.add(adopter);
        return adopter;
    }

    private User saveUser(UserDto registerRequest) {
        User user;
        switch (registerRequest.getRole()) {
            case MANAGER -> {
                user = saveManager(registerRequest);
            }
            case STAFF -> {
                user = saveStaff(registerRequest);
            }
            case ADOPTER -> {
                user = saveAdopter(registerRequest);
            }
            default -> throw new IllegalStateException("Unexpected value: " + registerRequest.getRole());
        }

        return user;
    }

    public AuthenticationResponse signUp(UserDto registerRequest) {
        removeIfNotEnabled(registerRequest.getEmail());
        handleException(registerRequest.getEmail());
        User user = saveUser(registerRequest);
        String verifyCode = createCode();
        String jwt = generateTokenForSignUp(user, verifyCode);
        System.out.println(jwtService.extractVerifyCode(jwt));
        if (registerRequest.isSignInWithEmail()) {
            user.setEnabled(true);
            userRepositoryService.update(user);
        } else {
            emailSenderService.sendMail(user.getEmail(),
                    "Verification Code", user.getFirstName(), verifyCode);
        }

        return AuthenticationResponse.builder()
                .id(user.getId())
                .token(jwt)
                .role(user.getRole().toString())
                .shelterId(getShelterId(user))
                .build();
    }

    public AuthenticationResponse signIn(AuthenticationRequest authenticationRequest, boolean withGmail) {
        User user = userRepositoryService.findByEmail(authenticationRequest.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        if (!user.isEnabled()) {
            throw new ForbiddenException();
        }
        if (user.isSignInWithEmail() != withGmail) {
            throw new ForbiddenException();
        }
        String jwt = generateTokenForSignIn(user);

        return AuthenticationResponse.builder()
                .id(user.getId())
                .token(jwt)
                .role(user.getRole().toString())
                .shelterId(getShelterId(user))
                .build();
    }

    public int getShelterId(User user) {
        switch (user.getRole()) {
            case MANAGER -> {
                Manager manager = (Manager) user;
                return manager.getShelter().getId();
            }
            case STAFF -> {
                Staff staff = (Staff) user;
                return staff.getShelter().getId();
            }
            case ADOPTER -> {
                return 0;
            }
            default -> throw new IllegalStateException("Unexpected value: " + user.getRole());
        }
    }



    private void putEnable(String mail) {
        User user = userRepositoryService.findByEmail(mail);
        user.setEnabled(true);
        userRepositoryService.update(user);
    }

    public Boolean verifyCode(VerifyRequest verifyRequest) {
        Boolean isEqual = verifyRequest.getVerifyCode().equals(jwtService.extractVerifyCode(verifyRequest.getToken()));
        if (isEqual) {
            putEnable(jwtService.extractUserName(verifyRequest.getToken()));
        } else {
            throw new ForbiddenException();
        }
        return isEqual;
    }

}
