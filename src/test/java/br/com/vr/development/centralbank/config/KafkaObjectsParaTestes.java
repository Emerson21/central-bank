package br.com.vr.development.centralbank.config;

import br.com.vr.development.centralbank.inbound.dto.events.TransferenciaEvent;
import br.com.vr.development.centralbank.inbound.dto.events.TransferenciaSolicitadaEvent;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import static java.util.Collections.singletonList;

@Configuration
public class KafkaObjectsParaTestes {

    @Value("${topico.transferencia.solicitada}")
    private String topicoTransferenciaSolicitada;

    @Autowired
    private KafkaConfig kafkaConfig;

    @Bean
    KafkaTemplate<String, TransferenciaEvent> kafkaTestTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(kafkaConfig.produtorConfigs()));
    }

    @Bean
    KafkaConsumer<String, TransferenciaSolicitadaEvent> transferenciaSolicitadaConsumer() {
        KafkaConsumer<String, TransferenciaSolicitadaEvent> consumidor =
            new KafkaConsumer<>(kafkaConfig.consumerConfigs(TransferenciaSolicitadaEvent.class));

        consumidor.subscribe(singletonList(topicoTransferenciaSolicitada));

        return consumidor;
    }

}
