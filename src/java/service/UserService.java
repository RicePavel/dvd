/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDao;
import entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import support.EntityValidator;

/**
 *
 * @author Rice Pavel
 */
@Service
@Transactional
public class UserService {

  @Autowired
  private UserDao userDao;

  @Autowired
  private EntityValidator validator;

  /**
   *
   * @return вернет текущего авторизованного пользователя. Может вернуть null
   */
  public User getCurrentUser() {
    return userDao.getUserByLogin(getUserName());
  }

  public void registration(String login, String password, String name, String surname, List<String> errors) {
    if (!existUser(login)) {
      User user = new User();
      user.setLogin(login);
      PasswordEncoder encoder = new Md5PasswordEncoder();
      String hash = encoder.encodePassword(password, "");
      user.setPassword(hash);
      user.setName(name);
      user.setSurname(surname);
      validator.validate(errors, user);
      if (errors.isEmpty()) {
        userDao.save(user);
      }
    } else {
      errors.add("Пользователь с таким логином уже существует!");
    }
  }

  private boolean existUser(String login) {
    return (userDao.getUserByLogin(login) != null);
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
