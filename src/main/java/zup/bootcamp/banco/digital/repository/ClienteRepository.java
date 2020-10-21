package zup.bootcamp.banco.digital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zup.bootcamp.banco.digital.model.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {
}
