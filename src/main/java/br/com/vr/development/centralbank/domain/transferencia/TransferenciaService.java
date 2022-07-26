package br.com.vr.development.centralbank.domain.transferencia;

import br.com.vr.development.centralbank.domain.transacao.TransacaoMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TransferenciaService {

    TransferenciaVisitor transferenciaVisitor;

    public void processar(TransacaoMessage transacaoMessage) {
        transferenciaVisitor.publishTransferenciaEvent(transacaoMessage.buildEvent());
    }

}
