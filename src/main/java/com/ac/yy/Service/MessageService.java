package com.ac.yy.Service;

import com.ac.yy.DTO.ResponseDTO;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    final DefaultMessageService messageService;

    public MessageService() {
        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
        this.messageService = NurigoApp.INSTANCE.initialize("NCS2DEOQ72FKAVR3", "9LJKFUKEUNCT9AQAULL22EZPSFZCOWUC", "https://api.coolsms.co.kr");
    }

    public ResponseDTO<?> sendOne(String toNumber, String randomNumber) {
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01094837519");
        message.setTo(toNumber);
        message.setText("[Y&Y Academy] 인증번호 [" + randomNumber + "]를 입력해주세요.");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        if(response.getStatusCode() == "2000") {
            return ResponseDTO.setSuccess("Send Message Success!", null);
        }
        else {
            return ResponseDTO.setFailed("Send Message Fail!");
        }
    }
}
