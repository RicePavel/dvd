/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rice Pavel
 */
@Service
@Transactional
public class UserService {
  
  @Autowired
  private UserDao userDao;
  
  /**
   * 
   * @return вернет текущего авторизованного пользователя. Может вернуть null
   */
  public User getCurrentUser() {
    return userDao.getUserByLogin(getUserName());
  }
  
  public void registration(String login, String password, String name, String surname, String middlename) {
    User user = new User();
    user.setLogin(login);
    user.setPassword(password);
    user.setName(name);
    user.setSurname(surname);
    user.setMiddlename(middlename);
    userDao.save(user);
  }

  public static String getUserName() {
    String username = "";

    SecurityContext context = SecurityContextHolder.getContext();
    if (context != null) {
      Authentication auth = context.getAuthentication();
      if (auth != null) {
        Object principal = auth.getPrincipal();
        if (principal instanceof UserDetails) {
          username = ((UserDetails) principal).getUsername();
        } else {
          username = principal.toString();
        }
      }
    }

    return username;
  }
  
}
