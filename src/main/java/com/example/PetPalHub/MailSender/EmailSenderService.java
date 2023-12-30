package com.example.PetPalHub.MailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to, String subject, String name, String verifyCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(
                "Hello\t" + name + "\n\n" + "Thanks for signing up with PET PAL HUB\n\n" +
                        "To verify your email please use the next code" + "\n\n" + "Verification Code :\t" + verifyCode + "\n\n" + "We look forward to see you in next event\n\n" + "Sincerely,\n" +
                        "PET PAL HUB Team"
        );
        javaMailSender.send(message);
    }

    public void sendAcceptanceMail(String to, String name, String petName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Congratulations! Your Pet Adoption Application Has Been Accepted");
        message.setText(
                "Dear \t" + name + ",\n\n" +
                        "We hope this email finds you well. We are delighted to inform you that your application for "+petName+" has been carefully reviewed, and we are thrilled to let you know that it has been accepted.\n\n"  +
                        "At PET PAL HUB, we understand the significance of finding the perfect companion, and we believe that you and your future furry friend will make a wonderful match. Your commitment to providing a loving home to a pet in need has not gone unnoticed, and we are confident that this will be the beginning of a beautiful journey.\n\n" +

                        "We look forward to witnessing the joy and happiness that your new pet will bring into your life.\n\n" +
                        "Best regards,\n" +
                        "PET PAL HUB Team"
        );
        javaMailSender.send(message);
    }
    public void sendRejectionMail(String to, String name, String petName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Notice Regarding Your Pet Adoption Application");
        message.setText(
                "Dear\t" + name + "\n\n" +
                        "We trust this message finds you well. Thank you for your recent application for "+petName+" with PET PAL HUB. We appreciate the time and effort you invested in the process.\n\n" +
                        "After careful consideration, we regret to inform you that your application for pet adoption has not been approved at this time. Please understand that this decision was not made lightly, and it in no way diminishes our appreciation for your interest in providing a loving home to a pet in need." +
                        "\n\n" +
                        "The adoption process is a thorough and competitive one, and unfortunately, we have limited resources and a responsibility to ensure the welfare of both our animals and potential adopters. We understand that this news may be disappointing, and we sincerely appreciate your understanding.\n\n" +
                        "We understand the emotions that come with the adoption process, and we encourage you to consider reapplying in the future. Circumstances may change, and we believe that the perfect match is out there for everyone.\n" +
                        "\n" +
                        "Thank you for considering PET PAL HUB for your adoption journey. We appreciate your compassion and dedication to providing a loving home to pets in need."+ "\n\n" +
                        "Best regards,\n" +
                        "PET PAL HUB Team"
        );
        javaMailSender.send(message);
    }
}
