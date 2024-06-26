package com.joseg.mongoproject.service;

import com.joseg.mongoproject.domain.User;
import com.joseg.mongoproject.dto.UserDTO;
import com.joseg.mongoproject.repository.UserRepository;
import com.joseg.mongoproject.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> FindAll(){
        return repo.findAll();
    }

    public User findById ( String id){
        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
    }

    public User insert ( User obj){
        return repo.insert(obj);
    }

    public void delete (String id ){
        findById(id);
        repo.deleteById(id);
    }

    public User update (User obj){
        User newObj = findById(obj.getId());
        updateData(newObj,obj);
        return repo.save(newObj);
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public  User fromDTO (UserDTO objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }
}
