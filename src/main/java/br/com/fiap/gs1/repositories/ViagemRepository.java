package br.com.fiap.gs1.repositories;

import br.com.fiap.gs1.models.ViagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ViagemRepository extends JpaRepository<ViagemModel, Long> {

}
