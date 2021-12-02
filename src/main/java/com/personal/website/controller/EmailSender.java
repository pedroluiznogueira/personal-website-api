package com.personal.website.controller;

import com.personal.website.domain.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/contact")
@CrossOrigin("*")
@Controller
public class EmailSender {

    @Autowired
    SpringTemplateEngine templateEngine;

    @Autowired
    private JavaMailSender sender;

    @RequestMapping("/me")
    public @ResponseBody
    Details sendMailProfessor(@RequestBody Details details) throws Exception {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Map<String, Object> model = new HashMap<>();
        model.put("name", details.getName());
        model.put("email", details.getEmail());
        model.put("info", details.getInfo());

        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process("contact", context);

        try {
            helper.setTo("peluiznogueiram@hotmail.com");
            helper.setText(html, true);
            helper.setSubject("Contact Personal Website");
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);

        return details;

    }
}
