package com.kcare.kcare.auth;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kcare.kcare.common.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller ", description = "Handles user authentication and account management")
public class AuthenticationController {

    private final AuthenticationService service;

    @Operation(summary = "Register a new user", description = "Registers a new user with the provided registration details and sends a confirmation Otp on Email")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Response<RegistrationRequest>> register(@RequestBody @Valid RegistrationRequest request)
            throws MessagingException, IOException {
        return ResponseEntity.ok(service.register(request));
    }

    @Operation(summary = "Verify OTP", description = "Verifies the OTP provided by the user.")
    @PostMapping("/otp-verificiation")
    public ResponseEntity<Response<OtpVerficationRequest>> otpVerification(
            @RequestBody @Valid OtpVerficationRequest otpVerficationRequest) {
        return ResponseEntity.ok(service.otpVerification(otpVerficationRequest));
    }

    @Operation(summary = "Authenticate a user", description = "Authenticates a user with the provided credentials and returns an authentication token.")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticaionRequest request,
            HttpServletResponse response)
            throws MessagingException, IOException {
        return ResponseEntity.ok(service.authenticate(request, response));
    }

    @Operation(summary = "Resend OTP", description = "Resends the OTP to the user's email")
    @PostMapping("/resendOtp")
    public ResponseEntity<Response<ResendOtpRequest>> resendOtp(@RequestBody @Valid ResendOtpRequest resendOtpRequest)
            throws MessagingException, IOException {
        return ResponseEntity.ok(service.resendOtp(resendOtpRequest));
    }

    @Operation(summary = "Initiate Password Reset", description = "Initiates the password reset process by sending a reset link to the user's email.")

    @PostMapping("/initiatePasswordReset")
    public ResponseEntity<Response<ForgetPasswordRequest>> forgetPasword(
            @RequestBody @Valid ForgetPasswordRequest forgetPassword)
            throws MessagingException, IOException {

        return ResponseEntity.ok(service.forgetPassword(forgetPassword));
    }

    @Operation(summary = "Set New Password", description = "Sets a new password using the provided reset token and new password.")

    @PatchMapping("/setNewPasword")
    public ResponseEntity<Response<ResetPasswordRequest>> resetForgottendPassword(
            @RequestBody @Valid ResetPasswordRequest resetPasswordRequest) {

        return ResponseEntity.ok(service.resetForgottendPassword(resetPasswordRequest));

    }

}
