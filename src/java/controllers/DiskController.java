/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Disk;
import entity.TakenItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.DiskService;

/**
 *
 * @author Rice Pavel
 */
@RequestMapping("/Disk")
@Controller
public class DiskController {

  @Autowired
  private DiskService diskService;

  @RequestMapping("/free")
  public String freeDisks(Map<String, Object> model) {
    List<Disk> diskList = diskService.getFreeDisks();
    model.put("diskList", diskList);
    return "disk_free_list";
  }
  
  @RequestMapping("/delete")
  public String delete(Map<String, Object> model, Long diskId) {
    diskService.delete(diskId);
    return "redirect:/Disk/allMyDiskList";
  }

  @RequestMapping("/add")
  public String addDisk(Map<String, Object> model, @RequestParam(value = "name", required = false) String name, 
          @RequestParam(value = "description", required = false) String description,
          String submit) {
    List<String> errors = new ArrayList();
    if (submit != null && !submit.isEmpty()) {
      Disk disk = new Disk();
      disk.setDescription(description);
      disk.setName(name);
      diskService.addDisk(disk, errors);
      if (errors.isEmpty()) {
        model.put("success", "true");
      }
      model.put("errors", errors);
    }
    if (errors.isEmpty()) {
      model.put("disk", new Disk());
    }
    return "disk_add";
  }
  
  /*
  @RequestMapping("/add")
  public String addDisk(Map<String, Object> model, @ModelAttribute("disk") Disk disk, String submit) {
    List<String> errors = new ArrayList();
    if (submit != null && !submit.isEmpty()) {
      diskService.addDisk(disk, errors);
      if (errors.isEmpty()) {
        model.put("success", "true");
      }
      model.put("errors", errors);
    }
    if (errors.isEmpty()) {
      model.put("disk", new Disk());
    }
    return "disk_add";
  }
  */

  /**
   * диски, которые взял пользователь
   *
   * @param model
   * @return
   */
  @RequestMapping("/takeList")
  public String getTakeDiskList(Map<String, Object> model) {
    List<Disk> diskList = diskService.getTakeDiskList();
    model.put("diskList", diskList);
    return "disk_take_list";
  }

  @RequestMapping("/allMyDiskList")
  public String getAllDiskList(Map<String, Object> model) {
    List<Object[]> list = diskService.getAllMyDiskList();
    model.put("list", list);
    return "disk_all_my_list";
  }

  /**
   * взять диск
   *
   * @param model
   * @param diskId
   * @return
   */
  @RequestMapping("/take")
  public String takeDisk(Map<String, Object> model, 
          @RequestParam("diskId") Long diskId) {
    diskService.takeDisk(diskId);
    return "redirect:/Disk/free";
  }

  /**
   * вернуть диск обратно владельцу
   *
   * @param model
   * @param diskId
   * @return
   */
  @RequestMapping("/toOwner")
  public String diskToOwner(Map<String, Object> model, 
          @RequestParam("diskId") Long diskId) {
    diskService.diskToOwner(diskId);
    return "redirect:/Disk/takeList";
  }

  /**
   * диски, которые у пользователя взяли другие
   *
   * @param model
   * @return
   */
  @RequestMapping("/takeOthers")
  public String getTakeOthersDiskList(Map<String, Object> model) {
    List<TakenItem> takenItemList = diskService.getTakeOthersDiskList();
    model.put("takenItemList", takenItemList);
    return "disk_take_others_list";
  }

}
