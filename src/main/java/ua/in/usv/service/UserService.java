package ua.in.usv.service;

import ua.in.usv.entity.CustomUser;

public interface UserService {
    CustomUser findByLogin(String login);
    boolean existsByLogin(String login);
    void addUser(CustomUser customUser);
    void updateUser(CustomUser customUser);
}
