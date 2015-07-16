/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserService;
import support.StringUtils;

/**
 *
 * @author Rice Pavel
 */
@Controller
@RequestMapping("/User")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping("/registration")
  public String registration(Map<String, Object> model,
          @RequestParam(value = "login", required = false) String login,
          @RequestParam(value = "password", required = false) String password,
          @RequestParam(value = "password2", required = false) String password2,
          @RequestParam(value = "name", required = false) String name,
          @RequestParam(value = "surname", required = false) String surname,
          @RequestParam(value = "middlename", required = false) String middlename,
          @RequestParam(value = "submit", required = false) String submit) {
    List<String> errors = new ArrayList();
    if (StringUtils.notEmpty(submit)) {
      if (StringUtils.notEmpty(login) && StringUtils.notEmpty(password) && StringUtils.notEmpty(password2)
              && StringUtils.notEmpty(name) && StringUtils.notEmpty(surname) && StringUtils.notEmpty(middlename)) {
        if (password.equals(password2)) {
          userService.registration(login, password, name, surname, middlename);
        } else {
          errors.add("Введенные пароли не совпадают");
        }
      } else {
        errors.add("Не все параметры переданы");
      }
      if (errors.isEmpty()) {
        model.put("success", "true");
      }
      model.put("errors", errors);
    }
    return "registration";
  }

}
