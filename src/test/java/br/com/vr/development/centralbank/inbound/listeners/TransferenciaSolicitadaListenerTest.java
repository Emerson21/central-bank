package br.com.vr.development.centralbank.inbound.listeners;


import br.com.vr.development.centralbank.commum.dto.TransacaoMessageDTO;
import br.com.vr.development.centralbank.domain.KafkaTransferenciaMessageSender;
import br.com.vr.development.centralbank.domain.transacao.TransacaoMessage;
import br.com.vr.development.centralbank.domain.transferencia.TransferenciaService;
import br.com.vr.development.centralbank.enums.TipoTransferencia;
import br.com.vr.development.centralbank.inbound.dto.events.TransferenciaEvent;
import br.com.vr.development.centralbank.inbound.dto.events.TransferenciaRecebidaEvent;
import br.com.vr.development.centralbank.inbound.dto.events.TransferenciaSolicitadaEvent;
import br.com.vr.development.centralbank.inbound.listener.TransferenciaSolicitadaListener;
import br.com.vr.development.centralbank.infrastructure.TransferenciaMessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DirtiesContext
@ExtendWith(MockitoExtension.class)
@EmbeddedKafka
@SpringBootTest(properties = "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}")
public class TransferenciaSolicitadaListenerTest {

    @SpyBean
    private TransferenciaSolicitadaListener listener;

    @MockBean
    private TransferenciaMessageRepository transferenciaMessageRepository;

    @MockBean
    private TransferenciaService transferenciaService;

    @MockBean
    private KafkaTransferenciaMessageSender kafkaMessageSender;

    @Autowired
    KafkaTemplate<String, TransferenciaEvent> kafkaTestTemplate;

    @Value("${topico.transferencia.solicitada}")
    private String topicoTransferenciaSolicitada;

    @Captor
    private ArgumentCaptor<TransferenciaSolicitadaEvent> captorTransferenciaSolicitada;

    @Test
    void deveReceberUmEventoDeTransferenciaSolicitada() {
        UUID uuid = UUID.randomUUID();

        TransacaoMessageDTO.Banco banco = new TransacaoMessageDTO.Banco(
            new TransacaoMessageDTO.NomeFantasia("Banco teste"),
            "123");

        TransacaoMessageDTO.ContaOrigem contaOrigem = new TransacaoMessageDTO.ContaOrigem(
            new TransacaoMessageDTO.NomeFantasia("Teste teste"),
            "123",
            banco
        );

        TransacaoMessageDTO.ContaDestino contaDestino = new TransacaoMessageDTO.ContaDestino(
            banco,
            new TransacaoMessageDTO.AgenciaBancaria(),
            12345678,
            0,
            "Nome correntista",
            new TransacaoMessageDTO.Cpf("00000000191", "CPF")
        );

        TransacaoMessageDTO transacaoMessageDTO = new TransacaoMessageDTO(
            null,
            uuid,
            new TransacaoMessageDTO.Valor(new BigDecimal("50")),
            contaOrigem,
            contaDestino,
            TipoTransferencia.PIX.name()
        );

        TransferenciaSolicitadaEvent evento
            = new TransferenciaSolicitadaEvent(UUID.randomUUID(), transacaoMessageDTO);

        kafkaTestTemplate.send(topicoTransferenciaSolicitada, evento);

        verify(listener, timeout(5000)).receberEventoTransferenciaSolicitadaEvent(captorTransferenciaSolicitada.capture());
        assertThat(captorTransferenciaSolicitada.getValue()).isEqualTo(evento);

        verify(transferenciaMessageRepository, times(1)).findById(anyString());
        verify(kafkaMessageSender, times(1)).publish(any(TransferenciaRecebidaEvent.class));
        verify(transferenciaService, times(1)).processar(any(TransacaoMessage.class));
    }


}
