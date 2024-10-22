package com.example.BookingService.service;

import com.example.BookingService.filter.UserFilter;
import com.example.BookingService.model.entity.Client;
import com.example.BookingService.model.entity.Role;
import com.example.BookingService.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    @Autowired
    private ClientRepository repository;

    private final PasswordEncoder passwordEncoder;

    public Client findByName(String name) {
        return repository.findByName(name).orElseThrow();
    }

    public boolean checkByNameAndEmail(String name, String email) {
        return repository.existsByNameAndEmail(name, email);
    }

    public List<Client> filterBy(UserFilter filter) {
        return repository.findAll(PageRequest.of(filter.getPageNumber(), filter.getPageSize())).toList();
    }

    public Client findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Client save(Client user) {
        return repository.save(user);
    }

    public Client createNewAccount(Client client, Role role) {
        client.setRoles(Collections.singletonList(role));
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        role.setClient(client);
        return repository.saveAndFlush(client);
    }

    public Client update(Client user) {
        Client existedUser = findById(user.getId());
        BeanUtils.copyProperties(user, existedUser);
        return repository.save(user);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
