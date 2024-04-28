package com.ivoronline.springboot_database_jdbctemplate_delete_batch.controllers;

import com.ivoronline.springboot_database_jdbctemplate_delete_batch.dto.PersonDTO;
import com.ivoronline.springboot_database_jdbctemplate_delete_batch.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {

  //PROPERTIES
  @Autowired private MyService myService;

  //=========================================================================================================
  // DELETE
  //=========================================================================================================
  @ResponseBody
  @GetMapping("/delete")
  public int[] delete() {
    int[]  deletedRecords = myService.delete(createRecords());
    return deletedRecords;
  }

  //=========================================================================================================
  // DELETE WITH BATCH SIZE
  //=========================================================================================================
  @ResponseBody
  @GetMapping("/deleteWithBatchSize")
  public int[][] deleteWithBatchSize() {
    int[][] deletedRecords = myService.deleteWithBatchSize(createRecords());
    return  deletedRecords;
  }

  //=========================================================================================================
  // CREATE RECORDS
  //=========================================================================================================
  public List<PersonDTO> createRecords() {

    //CREATE RECORDS TO DELETE
    PersonDTO       jill    = new PersonDTO(0, "John" , 10);
    PersonDTO       bill    = new PersonDTO(0, "Bill" , 20);
    PersonDTO       susan   = new PersonDTO(0, "Susan", 30);

    //CREATE LIST
    List<PersonDTO> records = new ArrayList<>();
                    records.add(jill);
                    records.add(bill);
                    records.add(susan);

    //RETURN LIST
    return records;

  }

}



