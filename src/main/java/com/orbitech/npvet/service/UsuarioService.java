package com.orbitech.npvet.service;

import com.orbitech.npvet.dto.UsuarioCadastrarDTO;
import com.orbitech.npvet.dto.UsuarioDTO;
import com.orbitech.npvet.entity.Role;
import com.orbitech.npvet.entity.Usuario;
import com.orbitech.npvet.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    private final PasswordEncoder passwordEncoder;


    private final ModelMapper mapper = new ModelMapper();

    public UsuarioDTO toUsuarioDTO(Usuario usuarioEntidade){
        return mapper.map(usuarioEntidade, UsuarioDTO.class);
    }

    public UsuarioCadastrarDTO toUsuarioCadastrarDTO(Usuario usuarioEntidade){
        return mapper.map(usuarioEntidade, UsuarioCadastrarDTO.class);
    }

    public Usuario toUsuarioEntidade(UsuarioDTO usuarioDTO){
        return mapper.map(usuarioDTO, Usuario.class);
    }

    public Usuario toUsuarioEntidade(UsuarioCadastrarDTO usuarioDTO){
        return mapper.map(usuarioDTO, Usuario.class);
    }


    public UsuarioDTO getById(String id){
        return toUsuarioDTO(repository.findById(id).orElse(null));
   }

    public UsuarioCadastrarDTO getByIdForDelete(String id){
        return toUsuarioCadastrarDTO(repository.findById(id).orElse(null));
    }

    public List<UsuarioDTO> getAll() {
        return repository.findAll().stream().map(this::toUsuarioDTO).toList();
    }
    @Transactional
    public UsuarioDTO create(UsuarioCadastrarDTO usuarioCadastrarDTO, Usuario usuarioAutenticado) {

        String encodedPassword = passwordEncoder.encode(usuarioCadastrarDTO.getPassword());

        usuarioCadastrarDTO.setPassword(encodedPassword);

        Usuario usuarioByCpf = repository.findUsuarioByCpf(usuarioCadastrarDTO.getCpf());

        Assert.isTrue(usuarioByCpf == null, String.format("Usuário com o CPF: {%s} já existe!",usuarioCadastrarDTO.getCpf()));
        UsuarioDTO usuarioDT = toUsuarioDTO(repository.save(toUsuarioEntidade(usuarioCadastrarDTO)));
        log.info("USUÁRIO:" + usuarioDT.getNome() + "NOME:" +usuarioDT.getNome()+ "USERNAME:" + usuarioDT.getUsername() + "CPF:" + usuarioDT.getCpf() + "| Criado por:" + usuarioAutenticado.getNome() + " "+ usuarioAutenticado.getId());
       return usuarioDT;
    }
    @Transactional
    public UsuarioDTO update(long id, UsuarioCadastrarDTO UsuarioCadastrarDTO, Usuario usuarioAutenticado) {
        UsuarioDTO usuarioDT = toUsuarioDTO(repository.save(toUsuarioEntidade(UsuarioCadastrarDTO)));
        log.info("USUÁRIO:" + usuarioDT.getNome() + "NOME:" +usuarioDT.getNome()+ "USERNAME:" + usuarioDT.getUsername() + "CPF:" + usuarioDT.getCpf() + "| Atualizado por:" + usuarioAutenticado.getNome() + " "+ usuarioAutenticado.getId());
        return usuarioDT;
    }

    public List<UsuarioDTO>getUsuarioByName(String nome){
        List<UsuarioDTO> retorno = repository.findAllUsuariosByNome(nome)
                .stream()
                .map(this::toUsuarioDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),String.format("Não encontramos nenhum usuario com o nome {%s}",nome));
        return retorno;
    }

    public List<UsuarioDTO>getTipoSecretaria(){
        List<UsuarioDTO> retorno = repository.findByRole(Role.SECRETARIA)
                .stream()
                .map(this::toUsuarioDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),"Nenhum usuário do tipo SECRETARIA cadastrado!");
        return retorno;
    }

    public List<UsuarioDTO>getTipoAdm(){
        List<UsuarioDTO>retorno = repository.findByRole(Role.ADMINISTRADOR)
                .stream()
                .map(this::toUsuarioDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),"Nenhum usuário do tipo ADMINISTRADOR cadastrado!");
        return retorno;
    }

    public List<UsuarioDTO>getTipoMedico(){
        List<UsuarioDTO>retorno = repository.findByRole(Role.MEDICO)
                .stream()
                .map(this::toUsuarioDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),"Nenhum usuário do tipo MÉDICO cadastrado!");
        return retorno;
    }

    public List<UsuarioDTO>getUsername(String username){
        List<UsuarioDTO>retorno = repository.findUsuarioByUsername(username)
                .stream()
                .map(this::toUsuarioDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),String.format("Nenhum usuário com o username: {%s} encontrado!",username.toUpperCase()));
        return retorno;
    }

    public UsuarioDTO getUsuarioByCpf(String cpf){
        UsuarioDTO retorno = toUsuarioDTO(repository.findUsuarioByCpf(cpf));
        Assert.notNull(retorno, String.format("Nenhum usuário com o CPF: {%s} localizado!",cpf));
        return retorno;
    }

    @Transactional
    public UsuarioDTO delete(String id, Usuario usuarioAutenticado){
        UsuarioCadastrarDTO userById = getByIdForDelete(id);
        userById.delete();
        UsuarioDTO usuarioDT = toUsuarioDTO(repository.save(toUsuarioEntidade(userById)));
        log.info("USUÁRIO:" + usuarioDT.getNome() + "NOME:" +usuarioDT.getNome()+ "USERNAME:" + usuarioDT.getUsername() + "CPF:" + usuarioDT.getCpf() + "| Deletado por:" + usuarioAutenticado.getNome() + " "+ usuarioAutenticado.getId());
        return usuarioDT;
    }

    @Transactional
    public UsuarioDTO activate(String id, Usuario usuarioAutenticado) {
        UsuarioDTO userById = getById(id);
        userById.activate();
        UsuarioDTO usuarioDT = toUsuarioDTO(repository.save(toUsuarioEntidade(userById)));
        log.info("USUÁRIO:" + usuarioDT.getNome() + "NOME:" +usuarioDT.getNome()+ "USERNAME:" + usuarioDT.getUsername() + "CPF:" + usuarioDT.getCpf() + "| ATIVADO por:" + usuarioAutenticado.getNome() + " "+ usuarioAutenticado.getId());
        return usuarioDT;
    }
}
