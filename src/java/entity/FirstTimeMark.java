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
import javax.persistence.Table;

/**
 *
 * @author Rice Pavel
 */
@Table(name = "first_time_mark")
@Entity
public class FirstTimeMark {
  
  @Id
  @javax.persistence.GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "first_time_mark_id")
  private Long firstTimeMarkId;
  
  
}
