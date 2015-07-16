/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rice Pavel
 */
@Repository
public class UserDao extends Dao<User> {
  
  @Override
  public Class getSupportedClass() {
    return User.class;
  }
  
  public User getUserByLogin(String login) {
    Criteria crit = currentSession().createCriteria(User.class);
    crit.add(Restrictions.eq("login", login));
    List<User> list = crit.list();
    if (!list.isEmpty()) {
      return list.get(0);
    } else {
      return null;
    }
  }
  
}
