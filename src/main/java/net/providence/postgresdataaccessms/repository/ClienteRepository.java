package net.providence.postgresdataaccessms.repository;

import net.providence.postgresdataaccessms.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
