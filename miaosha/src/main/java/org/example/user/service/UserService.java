package org.example.user.service;

import org.example.error.BussinessException;
import org.example.user.service.model.UserModel;

public interface UserService {

    public UserModel getUserById(Integer id);

    void register (UserModel userModel)throws BussinessException;

    public UserModel validationLogin(UserModel userModel) throws BussinessException;
}
