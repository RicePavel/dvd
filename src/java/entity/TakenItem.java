/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Rice Pavel
 */
@Entity
@Table(name = "taken_item")
public class TakenItem {
  
  @Id
  @javax.persistence.GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "taken_item_id")
  private Long takenItemId;
  
  @ManyToOne
  @NotNull
  @JoinColumn(name = "user_id")
  private User user;
  
  @ManyToOne
  @NotNull
  @JoinColumn(name = "disk_id")
  private Disk disk;

  public Long getTakenItemId() {
    return takenItemId;
  }

  public void setTakenItemId(Long takenItemId) {
    this.takenItemId = takenItemId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Disk getDisk() {
    return disk;
  }

  public void setDisk(Disk disk) {
    this.disk = disk;
  }
  
  
  
}
