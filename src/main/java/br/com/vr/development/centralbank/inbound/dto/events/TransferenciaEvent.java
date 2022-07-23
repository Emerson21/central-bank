package br.com.vr.development.centralbank.inbound.dto.events;

import br.com.vr.development.centralbank.commum.dto.TransacaoMessageDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
public class TransferenciaEvent {

    @JsonProperty("correlation_id")
    private UUID correlationId;

    @JsonProperty("transacaoMessage")
    private TransacaoMessageDTO transacaoMessage;

    public TransferenciaEvent(UUID correlationId, TransacaoMessageDTO transacaoMessage) {
        this.correlationId = correlationId;
        this.transacaoMessage = transacaoMessage;
    }

    public String correlationId() {
        return correlationId.toString();
    }


}
