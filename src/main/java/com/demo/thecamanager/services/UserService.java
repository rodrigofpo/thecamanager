package com.demo.thecamanager.services;

import com.demo.thecamanager.config.excepitionHandler.excepition.BusinessException;
import com.demo.thecamanager.dto.UserDTO;
import com.demo.thecamanager.entities.Book;
import com.demo.thecamanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String deleteUser(UserDTO user) {
        try {
            UserDetails userDetails = userRepository.findByCpf(user.cpf());
            return "Deletado com sucesso!";
        } catch (Exception e) {
            throw new BusinessException("Erro ao deletar Cliente!");
        }

    }
}