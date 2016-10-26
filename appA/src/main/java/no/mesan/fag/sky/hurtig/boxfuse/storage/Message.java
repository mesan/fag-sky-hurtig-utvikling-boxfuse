package no.mesan.fag.sky.hurtig.boxfuse.storage;

import java.time.LocalDateTime;

public class Message {
    public final Integer id;
    public final String message;
    public final LocalDateTime time;

    public Message(String message, LocalDateTime now) {
        this(null, message, now);
    }

    public Message(Integer id, String message, LocalDateTime time) {
        this.id = id;
        this.message = message;
        this.time = time;
    }
}
