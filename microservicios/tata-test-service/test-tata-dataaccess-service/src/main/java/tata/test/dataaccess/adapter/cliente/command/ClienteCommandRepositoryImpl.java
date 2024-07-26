package tata.test.dataaccess.adapter.cliente.command;

import java.util.concurrent.locks.StampedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.dataaccess.entities.ClienteEntity;
import tata.test.dataaccess.mappers.ClienteMapper;
import tata.test.dataaccess.repository.ClienteJpaRepository;
import tata.test.domain.application.ports.output.repository.cliente.command.ClienteCommandRepository;
import tata.test.exception.ClienteException;
import tata.test.record.request.ClienteRequestRecord;
import tata.test.record.response.ClienteResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClienteCommandRepositoryImpl implements ClienteCommandRepository {

  private final ClienteJpaRepository clienteJpaRepository;

  @Override
  @Transactional
  public ClienteResponseRecord createOrUpdate(ClienteRequestRecord clienteRequestRecord) {
    ClienteEntity entity;

    if (clienteRequestRecord.id() != null) {
      entity = updateExistingCliente(clienteRequestRecord);
    } else {

      entity = ClienteMapper.INSTANCE.requestRecordToEntity(clienteRequestRecord);
    }
    return ClienteMapper.INSTANCE.entityToResponseRecord(clienteJpaRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(Integer id) {
    clienteJpaRepository.deleteById(id);
  }

  private ClienteEntity updateExistingCliente(ClienteRequestRecord requestRecord) {
    ClienteEntity existingEntity =
        clienteJpaRepository
            .findById(requestRecord.id())
            .orElseThrow(() -> new ClienteException("Cliente no encontrado"));

    final StampedLock lock = existingEntity.getLock();
    long stamp = lock.writeLock();
    try {
      existingEntity.setContrasenia(requestRecord.contrasenia());
      existingEntity.setEstado(requestRecord.estado());

    } finally {
      lock.unlockWrite(stamp);
    }
    return existingEntity;
  }
}
