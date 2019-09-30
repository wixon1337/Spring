package hu.flowacademy.chat.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChatResource {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/socket")
    public ResponseEntity<?> useSimpleRest(@RequestBody Map<String, String> message) {
        if (message.containsKey("message")) {
            if (message.containsKey("toId") && message.get("toId") != null && !message.get("toId").equals("")) {
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/" + message.get("toId"), message);
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/" + message.get("fromId"), message);
                return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @MessageMapping("/send/message")
    public Map<String, String> useSocketCommunication(String message) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> messageConverted;
        try {
            messageConverted = mapper.readValue(message, Map.class);
        } catch (IOException e){
            messageConverted = null;
        }
        if (messageConverted != null) {
            if (messageConverted.containsKey("toId") && messageConverted.get("toId") != null && !messageConverted.get("toId").equals("")) {
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/" + messageConverted.get("toId"), message);
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/" + messageConverted.get("fromId"), message);
            }
        }
        return messageConverted;
    }

}
