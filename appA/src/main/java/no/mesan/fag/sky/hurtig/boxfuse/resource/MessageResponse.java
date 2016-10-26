package no.mesan.fag.sky.hurtig.boxfuse.resource;

import no.mesan.fag.sky.hurtig.boxfuse.storage.Message;

import java.time.LocalDateTime;

class MessageResponse {

    public final Integer id;
    public final String message;
    public final String time;

    private MessageResponse(Integer id, String message, LocalDateTime time) {
        this.id = id;
        this.message = message;
        this.time = time.toString();
    }

    static MessageResponse from(Message message) {
        return new MessageResponse(message.id, message.message, message.time);
    }
}
