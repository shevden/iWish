package com.ds.iwish.data.repository;

import com.ds.iwish.bean.Profile;
import com.ds.iwish.data.dao.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepository {

    public static final Logger LOG = LogManager.getLogger(UserRepository.class.getName());


    private UserDAO userDAO;


    public Profile getUserById(long pId){
        return getUserDAO().getUserById(pId);
    }

    public Profile getUserByEmail(String pEmail){
        LOG.info("Inside #getUserByEmail : (pEmail = {})", pEmail);
        Profile Profile = getUserDAO().getUserByEmail(pEmail);
        LOG.info("Leaving #getUserByEmail : (Profile = {})", Profile);
        return Profile;
    }

    public List<Profile> getUsersByName(String pName, int pPageSize, int pPageNum){
        return getUserDAO().getUsersByName(pName, pPageSize, pPageNum);
    }

    public Profile createUser(Profile pProfile){
        LOG.info("Inside #validateProfileForRegistration : (pProfile = {})", pProfile);
        Profile Profile = getUserDAO().createUser(pProfile);
        LOG.info("Leaving #validateProfileForRegistration : (Profile = {})", Profile);
        return Profile;
    }

    public Profile updateUser(Profile pProfile){
        return getUserDAO().updateUser(pProfile);
    }

    public boolean deleteUser(long pProfileId){
        return getUserDAO().deleteUser(pProfileId);
    }


    public UserDAO getUserDAO() {
        return userDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
