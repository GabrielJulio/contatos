package br.eti.gabrieljuliobs.contatos.repositories;

import br.eti.gabrieljuliobs.contatos.domain.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {
}
