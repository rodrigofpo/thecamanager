package com.demo.thecamanager.config.security.Auth;

import com.demo.thecamanager.config.security.token.TokenService;
import com.demo.thecamanager.entities.User;
import com.demo.thecamanager.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    PasswordEncoder encryptedPassword;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.cpf(), data.pwd());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponseDTO("Token gerado com sucesso.", token));
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid AuthenticationDTO data) {
        if (this.repository.findByCpf(data.cpf()) != null) return ResponseEntity.badRequest().build();

        String encode = this.encryptedPassword.encode(data.pwd());

        User newUser = new User();
        newUser.setCpf(data.cpf());
        newUser.setName(data.name());
        newUser.setPwd(encode);
        this.repository.save(newUser);

        return ResponseEntity.ok("Cadastrado com sucesso!");
    }

    @PostMapping("/recovery")
    public ResponseEntity recovery(@RequestBody @Valid AuthenticationDTO data) {
        User user = (User) repository.findByCpf(data.cpf());

        if (user == null) return ResponseEntity.notFound().build();

        String encode = this.encryptedPassword.encode(data.pwd());
        user.setPwd(encode);
        this.repository.save(user);

        return ResponseEntity.ok("Alterada com sucesso!");
    }
}
