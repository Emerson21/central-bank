package br.com.vr.development.centralbank.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Document(collection = "eventos_transacoes")
@ToString
public abstract class Event {

    @JsonIgnore
    @Id
    private String id;

    @JsonProperty("correlation_id")
    protected UUID correlationId;

    protected Event(UUID correlationId) {
        this.correlationId = correlationId;
    }

}
