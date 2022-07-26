package br.com.vr.development.centralbank.domain.transferencia;

import br.com.vr.development.centralbank.inbound.dto.events.*;

public interface TransferenciaVisitor {

    void publishTransferenciaEvent(ITransferenciaVisitor transferenciaVisitor);

    void visit(TransferenciaReprovadaEvent transferenciaReprovadaEvent);
    void visit(TransferenciaAprovadaEvent transferenciaAprovadaEvent);

}
