/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.FirstTimeMarkDao;
import entity.FirstTimeMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rice Pavel
 */
@Service
public class FirstTimeMarkService {
  
  @Autowired
  private FirstTimeMarkDao firstTimeMarkDao;
  
  /**
   * запушено ли приложение в первый раз
   * @return 
   */
  public boolean inFirstTime() {
    return (firstTimeMarkDao.getAll().isEmpty());
  }
  
  /**
   * сделать запись о том, что приложение запущено в первый раз
   */
  public void writeFirstTime() {
    FirstTimeMark mark = new FirstTimeMark();
    firstTimeMarkDao.save(mark);
  }
  
}
