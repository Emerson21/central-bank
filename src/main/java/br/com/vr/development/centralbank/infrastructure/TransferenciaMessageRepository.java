package br.com.vr.development.centralbank.infrastructure;

import br.com.vr.development.centralbank.inbound.dto.events.TransferenciaEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaMessageRepository extends MongoRepository<TransferenciaEvent, String> {
}
