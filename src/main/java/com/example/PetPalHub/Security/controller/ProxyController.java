package com.example.PetPalHub.Security.controller;


import com.example.PetPalHub.Dto.UserDto;
import com.example.PetPalHub.Security.Service.ProxyService;
import com.example.PetPalHub.Security.authenticationMessages.AuthenticationRequest;
import com.example.PetPalHub.Security.authenticationMessages.AuthenticationResponse;
import com.example.PetPalHub.Security.authenticationMessages.VerifyRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proxy/")
@CrossOrigin("*")

@RequiredArgsConstructor
public class ProxyController {

    private final ProxyService proxyService;

    @PostMapping("basicSignUp")
    public ResponseEntity<AuthenticationResponse> basicSignUp(@RequestBody UserDto registerRequest) {
        return new ResponseEntity<AuthenticationResponse>(proxyService.signUp(registerRequest), HttpStatus.OK);
    }

    @PostMapping("verifyMail")
    public ResponseEntity<Boolean> verifyMail(@RequestBody VerifyRequest verifyRequest) {
        return new ResponseEntity<>(proxyService.verifyCode(verifyRequest), HttpStatus.OK);
    }

    @PostMapping("basicSignIn")
    public ResponseEntity<AuthenticationResponse> basicSignIn(@RequestBody AuthenticationRequest authenticationRequest) {
        return new ResponseEntity<AuthenticationResponse>(proxyService.signIn(authenticationRequest, false), HttpStatus.OK);
    }

    @PostMapping("signUpWithGmail")
    public ResponseEntity<AuthenticationResponse> signUpWithGmail(@RequestBody UserDto registerRequest) {
        return new ResponseEntity<AuthenticationResponse>(proxyService.signUp(registerRequest), HttpStatus.OK);
    }

    @PostMapping("signInWithGmail")
    public ResponseEntity<AuthenticationResponse> signInWithGmail(@RequestBody AuthenticationRequest authenticationRequest) {
        return new ResponseEntity<AuthenticationResponse>(proxyService.signIn(authenticationRequest, true), HttpStatus.OK);
    }
    @GetMapping("test")
    public ResponseEntity<String> test(@NonNull HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        System.out.println(authHeader);
        return new ResponseEntity<String>("test", HttpStatus.OK);
    }
}
