package com.ac.yy.Controller;

import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.DTO.VerifyDTO;
import com.ac.yy.Service.MessageService;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/message")
public class MessageController {
    @Autowired MessageService messageService;

    // 단일 메시지 발송
    @PostMapping("/send")
    ResponseDTO<?> sendOne(@RequestBody VerifyDTO dto) {
        ResponseDTO result = messageService.sendOne(dto.getToNumber(), dto.getRandomNumber());
        return result;
    }

}
