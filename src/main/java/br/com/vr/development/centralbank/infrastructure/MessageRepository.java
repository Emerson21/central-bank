package br.com.vr.development.centralbank.infrastructure;

import br.com.vr.development.centralbank.commum.dto.TransacaoMessageDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<TransacaoMessageDTO, String> {
}
