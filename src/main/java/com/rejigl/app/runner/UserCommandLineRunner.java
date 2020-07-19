package com.rejigl.app.runner;

import com.rejigl.app.entity.User;
import com.rejigl.app.repository.UserRepository;
import com.rejigl.app.service.UserDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

    private final static Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDaoService userDaoService;

    @Override
    public void run(String... args) throws Exception {
        /*userRepository.save(new User("Sachin","Cricket"));
        userRepository.save(new User("Alla Rakha","Music"));
        userRepository.save(new User("Alex","Football"));

        for (User user:userRepository.findAll()){
            log.info(user.toString());
        }

        for (User user:userRepository.findByRole("Cricket")){
            log.info("Cricketer: " +user.toString());
        }*/

        User user = new User("Ganguly", "Captain" );
        long insert = userDaoService.insert(user);
        log.info("New User Id = " + insert);


    }
}
