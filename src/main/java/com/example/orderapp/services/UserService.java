package com.example.orderapp.services;

import com.example.orderapp.models.User;
import com.example.orderapp.models.UserModel;

public interface UserService {
    User createUser(UserModel user);

    User readUser();
    User getLoggedInUser();
}
