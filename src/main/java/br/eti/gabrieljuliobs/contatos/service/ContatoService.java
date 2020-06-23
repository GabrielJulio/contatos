package br.eti.gabrieljuliobs.contatos.service;

import br.eti.gabrieljuliobs.contatos.domain.Contato;
import br.eti.gabrieljuliobs.contatos.repositories.ContatoRepository;
import br.eti.gabrieljuliobs.contatos.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public Contato buscar(Integer id) {
        Optional<Contato> contato = contatoRepository.findById((id));
        return contato.orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado um contato com id igual à ID.".replace("ID", Integer.toString(id))));
    }

    public Contato insert(Contato contato) {
        contato.setId(null);
        return contatoRepository.save(contato);
    }

    public Contato update(Contato contato) {
        buscar(contato.getId());
        return contatoRepository.save(contato);
    }

    public void delete(Integer id) {
        buscar(id);
    }

    public List<Contato> findAll() {
        return contatoRepository.findAll();
    }
}
