package br.com.vr.development.centralbank.domain.transacao;


import br.com.vr.development.centralbank.commum.dto.TransacaoMessageDTO;
import br.com.vr.development.centralbank.enums.TipoTransferencia;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TransacaoMessage {

    private UUID correlationId;
    private TransacaoMessageDTO.Valor valor;
    private TransacaoMessageDTO.ContaOrigem contaOrigem;
    private TransacaoMessageDTO.ContaDestino contaDestino;
    private TipoTransferencia tipoTransferencia;

    public boolean valorEhMenorOuIgualAZero() {
        return valor.ehMenorOuIgualAZero();
    }

    public TransacaoMessageDTO toDTO() {
        return new TransacaoMessageDTO(this);
    }

//    public ITransferenciaVisitor buildEvent() {
//        if (this.valorEhMenorOuIgualAZero()) {
//            return new TransferenciaReprovadaEvent(correlationId, toDTO());
//        }
//
//        return new TransferenciaAprovadaEvent(correlationId, toDTO());
//    }
}
