package com.example.reto3.service;

import com.example.reto3.entities.Client;
import com.example.reto3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.getAll();
    }
    public Optional<Client> getClient(int id){
        return clientRepository.getClient(id);
    }

    public Client save(Client c){
        if(c.getId()==null){
            return clientRepository.save(c);

        }else {
            Optional<Client> e = clientRepository.getClient(c.getId());
            if (e.isPresent()){
                return c;
            }else {
                return clientRepository.save(c);
            }
        }
    }
    public Client update(Client c){
        if(c.getId()!=null){
            Optional<Client> q = clientRepository.getClient(c.getId());
            if (q.isPresent()){
                if(c.getName()!=null){
                    q.get().setName(c.getName());
                }
                if(c.getEmail()!=null){
                    q.get().setEmail(c.getEmail());
                }
                if(c.getAge()!=null){
                    q.get().setAge(c.getAge());
                }
                if(c.getPassword()!=null){
                    q.get().setPassword(c.getPassword());
                }
                clientRepository.save(q.get());
                return q.get();
            }else{
                return c;
            }

        }else{
            return  c;
        }
    }
    public boolean delete(int id){
        boolean flag=false;
        Optional<Client>p= clientRepository.getClient(id);
        if (p.isPresent()){
            clientRepository.delete(p.get());
            flag=true;
        }

        return flag;
    }
}