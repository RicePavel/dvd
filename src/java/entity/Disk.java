/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Rice Pavel
 */
@Entity
@Table(name = "disk")
public class Disk {

  @Id
  @javax.persistence.GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "disk_id")
  private Long diskId;

  @NotEmpty(message = "Не передан обязательный параметр - название")
  @Column(name = "name")
  private String name;
  
  @Column(name = "description")
  private String description;
  
  @NotNull
  @ManyToOne
  @JoinColumn(name = "owner_id")
  private User owner;
  
  @OneToMany(mappedBy = "disk")
  private List<TakenItem> takenItemList;

  public Long getDiskId() {
    return diskId;
  }

  public void setDiskId(Long diskId) {
    this.diskId = diskId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public List<TakenItem> getTakenItemList() {
    return takenItemList;
  }

  public void setTakenItemList(List<TakenItem> takenItemList) {
    this.takenItemList = takenItemList;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
