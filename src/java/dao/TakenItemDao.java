/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Disk;
import entity.TakenItem;
import entity.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rice Pavel
 */
@Repository
public class TakenItemDao extends Dao<TakenItem> {

  @Override
  public Class getSupportedClass() {
    return TakenItem.class;
  }
  
  public List<TakenItem> get(Disk disk, User user) {
    Criteria crit = getCriteriaDistinctRootEntity(TakenItem.class);
    crit.add(Restrictions.eq("disk.diskId", disk.getDiskId()));
    crit.add(Restrictions.eq("user.userId", user.getUserId()));
    return crit.list();
  }
  
  public List<TakenItem> getTakeOthersDiskList(User user) {
    Criteria crit = getCriteriaDistinctRootEntity(TakenItem.class);
    crit.createAlias("disk", "disk", JoinType.INNER_JOIN);
    crit.add(Restrictions.eq("disk.owner.userId", user.getUserId()));
    return crit.list();
  }
  
}
