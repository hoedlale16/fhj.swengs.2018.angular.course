package at.fh.ima.swengs.moviedbv3.controller;

import at.fh.ima.swengs.moviedbv3.dto.MessageDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MessageController {

    @MessageMapping("/new-message") //Anmkommende Messages
    @SendTo("/message") //Verteilen der Messages auf
    public MessageDTO message(MessageDTO message) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyy hh:mm:ss");
        message.setMessage(formatter.format(new Date()) + " (" + SecurityContextHolder.getContext().getAuthentication().getName()  + "): " + message.getMessage());
        return message;
    }

}
