/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DiskDao;
import dao.TakenItemDao;
import entity.Disk;
import entity.TakenItem;
import entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import support.EntityValidator;
import support.StringUtils;

/**
 *
 * @author Rice Pavel
 */
@Service
@Transactional
public class DiskService {

  @Autowired
  private UserService userService;

  @Autowired
  private DiskDao diskDao;

  @Autowired
  private TakenItemDao takenItemDao;

  @Autowired
  private EntityValidator validator;

  public List<Disk> getFreeDisks() {
    User currentUser = userService.getCurrentUser();
    return diskDao.getFreeDisks(currentUser.getUserId());
  }

  public void addDisk(Disk disk, List<String> errors) {
    User user = userService.getCurrentUser();
    disk.setOwner(user);
    validator.validate(errors, disk);
    if (errors.isEmpty()) {
      diskDao.save(disk);
    }
  }

  /**
   * диски, которые пользователь взял
   *
   * @param user
   * @return
   */
  public List<Disk> getTakeDiskList() {
    User user = userService.getCurrentUser();
    return diskDao.getDisksUserTake(user);
  }

  /**
   * диски, которыми пользователь владеет
   *
   * @param user
   * @return
   */
  public List<Disk> getDisksByOwner() {
    User user = userService.getCurrentUser();
    return diskDao.getDisksByOwner(user);
  }

  /**
   * диски, который пользователь отдал
   */
  public List<TakenItem> getTakeOthersDiskList() {
    User user = userService.getCurrentUser();
    return takenItemDao.getTakeOthersDiskList(user);
  }

  /**
   * получить все мои диски
   *
   * @return список массивов. Структура каждого массива: 0 => Disk, 1 => TakeItem (необязательно)
   */
  public List<Object[]> getAllMyDiskList() {
    User user = userService.getCurrentUser();
    return diskDao.getAllDiskList(user);
  }

  public void takeDisk(Long diskId) {
    User user = userService.getCurrentUser();
    TakenItem item = new TakenItem();
    Disk disk = diskDao.find(diskId);
    item.setDisk(disk);
    item.setUser(user);
    takenItemDao.save(item);
  }

  public void diskToOwner(Long diskId) {
    User user = userService.getCurrentUser();
    Disk disk = diskDao.find(diskId);
    List<TakenItem> takenItemList = takenItemDao.get(disk, user);
    for (TakenItem item : takenItemList) {
      takenItemDao.delete(item);
    }
  }

}
