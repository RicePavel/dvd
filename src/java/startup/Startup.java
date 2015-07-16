/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import service.FirstTimeMarkService;
import service.UserService;

/**
 *
 * @author Rice Pavel
 */
@Component
public class Startup implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private FirstTimeMarkService firstTimeMarkService;

  @Autowired
  private UserService userService;

  @Override
  public void onApplicationEvent(final ContextRefreshedEvent event) {
    if (firstTimeMarkService.inFirstTime()) {
      addTestUsers();
    }
  }

  private void addTestUsers() {
    String login = "user1";
    String password = "qwerty";
    String name = "Иван";
    String surname = "Иванов";
    List<String> errors = new ArrayList();
    userService.registration(login, password, name, surname, errors);
    login = "user2";
    password = "qwerty";
    name = "Петр";
    surname = "Петров";
    userService.registration(login, password, name, surname, errors);
  }

}
