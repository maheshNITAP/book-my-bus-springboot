package com.springboot.mybus.Service;

import com.springboot.mybus.DAO.UserDAO;
import com.springboot.mybus.model.User;
import com.springboot.mybus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> loginCheck(User user) {
        List<User> theUser;

        theUser=userDAO.loginCheck(user);
        return theUser;
    }
//
//    @Override
//    public List<User> findAll() {
//        return userRepository.findAll();
//
//    }
//
//    @Override
//    public User save(User user) {
//
//        return userRepository.save(user);
//    }
//
//    @Override
//    public Optional<User> findById(Integer theId) {
//
//        return userRepository.findById(theId);
//    }
//
//    @Override
//    public void deleteById(Integer theId) {
//    userRepository.deleteById(theId);
//    }
}
