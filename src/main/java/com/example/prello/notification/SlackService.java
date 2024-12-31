package com.example.prello.notification;

import com.example.prello.common.SessionName;
import com.slack.api.Slack;
import com.slack.api.model.Attachment;
import com.slack.api.model.Field;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.slack.api.webhook.WebhookPayloads.payload;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackService {

    private final HttpSession session;
    private final Slack slackClient = Slack.getInstance();

    @Value("${slack.webhook.url}")
    private String webhookUrl;

    // 슬랙 알림 보내는 메서드
    public void sendSlackNotification(String title, HttpServletRequest request, String msg) {
        try {
            slackClient.send(webhookUrl, payload(p -> p
                    .text(title)
                    .attachments(
                            List.of(generateSlackAttachment(request, msg))
                    )
            ));
        } catch (IOException slackError) {
            log.debug("Slack 통신과의 예외 발생");
        }
    }

    /**
     * 슬랙 메시지 전송
     **/
    private Attachment generateSlackAttachment(HttpServletRequest request, String msg) {
        String requestTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now());
        return Attachment.builder()
                .color("ff0000")
                .title(requestTime)
                .fields(List.of(
                                generateSlackField("보낸 유저 id", session.getAttribute(SessionName.USER_ID).toString()),
                                generateSlackField("Request URL", request.getRequestURL() + " " + request.getMethod()),
                                generateSlackField("메세지", msg)
                        )
                )
                .build();
    }

    // Field 생성 메서드
    private Field generateSlackField(String title, String value) {
        return Field.builder()
                .title(title)
                .value(value)
                .valueShortEnough(false)
                .build();
    }
}
