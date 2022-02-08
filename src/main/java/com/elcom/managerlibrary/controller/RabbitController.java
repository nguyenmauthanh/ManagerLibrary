package com.elcom.managerlibrary.controller;

import com.elcom.managerlibrary.rabbitmq.Pub_SubSender;
import com.elcom.managerlibrary.rabbitmq.Sender;
import com.elcom.managerlibrary.rabbitmq.WorkerSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rabbitmq")
public class RabbitController {

    @Autowired
    private Sender sender;

    @Autowired
    private WorkerSender workerSender;

    @Autowired
    private Pub_SubSender pub_subSender;

    @GetMapping(value = "/send")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        sender.send(message);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    @GetMapping(value = "/workerSend")
    public ResponseEntity<String> workerSendMessage(@RequestParam("message") String message){
        workerSender.send(message);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping(value = "/publiser")
    public ResponseEntity<String> pub_subSendMessage(@RequestParam("message") String message){
        pub_subSender.send(message);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
