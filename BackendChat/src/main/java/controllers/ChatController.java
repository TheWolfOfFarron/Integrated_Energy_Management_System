package ro.tuc.ds2020.controllers;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ro.tuc.ds2020.entities.Massage;

import java.util.Date;

@Controller
public class ChatController {
    @MessageMapping("/chat")
    @SendTo("/topic/mes")
    public Massage sendMessage(@Payload Massage charMessage){
            charMessage.setTimestamp(new Date());

            return charMessage;
    }

}
