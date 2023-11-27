package com.orbitech.npvet.auth;

import com.orbitech.npvet.dto.LoginDTO;
import com.orbitech.npvet.dto.UsuarioDTO;
import com.orbitech.npvet.entity.Usuario;
import com.orbitech.npvet.repository.UsuarioRepository;
import com.orbitech.npvet.service.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.file.AccessDeniedException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Value("${security.jwt.token}")
    private String key;
    @Value("${security.jwt.auth-url}")
    private String authTokenUrl;

    private PublicKey decodeSecret(String secret) throws InvalidKeyException {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(secret);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(spec);
        } catch (Exception e) {
            throw new InvalidKeyException("Erro ao usar a chave pública", e);
        }
    }
    @Transactional
    public UsuarioDTO login(LoginDTO loginDTO) throws AccessDeniedException, InvalidKeyException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        UsuarioDTO usuarioRetorno;
        Usuario usuarioBanco;
        String role = "NONE";
        String token;
        PublicKey publicKey = decodeSecret(key);

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add("client_id", "npvet-api");
        formData.add("username", loginDTO.getUsername());
        formData.add("password", loginDTO.getPassword());
        formData.add("grant_type", "password");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formData, headers);
        AuthResponse retorno = restTemplate.postForEntity(authTokenUrl,entity,AuthResponse.class).getBody();
        token = retorno.getAccess_token();
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(retorno.getAccess_token());

        Claims body = claimsJws.getBody();
        List<String> roles = (List<String>) body.get("realm_access", Map.class).get("roles");
        for (String r:
                roles) {
            if(r.equals("ADMIN") || r.equals("FUNCIONARIO")){
                role = r;
                break;
            }
        }
        if(role.equals("NONE")){
            System.out.println(role);
            throw new AccessDeniedException("Usuário sem permissões!");
        }
        String username = (String) body.get("preferred_username");
        String id = (String) body.get("sub");
        usuarioBanco = usuarioRepository.findById(id).orElse(null);
        if(usuarioBanco != null){
            usuarioRetorno = usuarioService.toUsuarioDTO(usuarioBanco);
            if(!usuarioBanco.roleStringGet().equals(role)){
                System.out.println(role);
                usuarioBanco.roleStringSet(role);
                usuarioRetorno = usuarioService.toUsuarioDTO(usuarioRepository.save(usuarioBanco));
            }
        }
        else{
            UsuarioDTO mock = new UsuarioDTO();
            mock.setId(id);
            mock.roleStringSet(role);
            mock.setUsername(username);
            usuarioRetorno = usuarioService.create(mock);
        }
        usuarioRetorno.setToken(token);
        return usuarioRetorno;
    }
}