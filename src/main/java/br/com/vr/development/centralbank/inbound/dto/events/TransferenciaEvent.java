package br.com.vr.development.centralbank.inbound.dto.events;

import br.com.vr.development.centralbank.commum.dto.TransacaoMessageDTO;
import br.com.vr.development.centralbank.domain.transferencia.ITransferenciaVisitor;
import br.com.vr.development.centralbank.domain.transferencia.TransferenciaVisitor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Document("transferencia_events")
public class TransferenciaEvent {

    @Id
    @JsonProperty("correlation_id")
    UUID correlationId;

    @JsonProperty("transacaoMessage")
    private TransacaoMessageDTO transacaoMessage;

    public TransferenciaEvent(UUID correlationId, TransacaoMessageDTO transacaoMessage) {
        this.correlationId = correlationId;
        this.transacaoMessage = transacaoMessage;
    }

    public String correlationId() {
        return correlationId.toString();
    }


    @Override
    public boolean equals(Object object) {
        if ( object != null && object instanceof TransferenciaEvent ) {
            return ((TransferenciaEvent) object).correlationId.equals(this.correlationId);
        }

        return false;
    }

}
