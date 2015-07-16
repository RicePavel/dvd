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
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rice Pavel
 */
@Repository
public class DiskDao extends Dao<Disk> {
  
  @Override
  public Class getSupportedClass() {
    return Disk.class;
  }
  
  public List<Disk> getFreeDisks(Long currentUserId) {
    /*
    DetachedCriteria subCrit = DetachedCriteria.forClass(TakenItem.class, "takenItem");
    subCrit.createAlias("takenItem.disk", "td");
    //subCrit.add(Property.forName("td.diskId").eq("11111"));
    
    Criteria crit = getCurrentSession().createCriteria(Disk.class, "disk");
    //crit.add(Subqueries.notExists(subCrit));
    return crit.list();
    */
    
    String hql = "from Disk d where not exists ( from TakenItem ti where ti.disk.diskId = d.diskId) and d.owner.userId != :userId ";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter("userId", currentUserId);
    return query.list();
  }
  
  public List<Object[]> getAllDiskList(User user) {
    /*
    Criteria crit = currentSession().createCriteria(Disk.class, "disk");
    crit.
    crit.createAlias("disk.takenItemList", "takenItem", JoinType.LEFT_OUTER_JOIN);
    crit.add(Restrictions.eq("disk.user.userId", user.getUserId()));
    
    return crit.list();
    */
    
    String hql = "select d, ti from Disk as d left join d.takenItemList as ti where d.owner.userId = :userId ";
    Query query = currentSession().createQuery(hql);
    query.setParameter("userId", user.getUserId());
    return query.list();
  }
  
  /**
   * диски, которые пользователь взял
   * @param user
   * @return 
   */
  public List<Disk> getDisksUserTake(User user) {
    /*
    DetachedCriteria subCrit = DetachedCriteria.forClass(TakenItem.class, "takenItem");
    subCrit.add(Property.forName("takenItem.disk.diskId").eqProperty("disk.diskId"));
    subCrit.add(Property.forName("takenItem.user.userId").eq(user.getUserId()));
    
    Criteria crit = getCurrentSession().createCriteria(Disk.class);
    crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    crit.add(Subqueries.exists(subCrit));
    
    return crit.list();
    */
    
    String hql = "from Disk as disk where exists (from TakenItem as ti where ti.disk.diskId = disk.diskId and ti.user.userId = :userId) ";
            
    Query query = currentSession().createQuery(hql);
    query.setParameter("userId", user.getUserId());
    return query.list();
  }
  
  /**
   * диски, которыми пользователь владеет
   * @param user
   * @return 
   */
  public List<Disk> getDisksByOwner(User user) {
    Criteria crit = getCurrentSession().createCriteria(Disk.class);
    crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    crit.add(Restrictions.eq("user.userId", user.getUserId()));
    return crit.list();
  }
  
}
