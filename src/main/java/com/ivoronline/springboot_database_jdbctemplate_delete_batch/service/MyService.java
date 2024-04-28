package com.ivoronline.springboot_database_jdbctemplate_delete_batch.service;

import com.ivoronline.springboot_database_jdbctemplate_delete_batch.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class MyService {

  //PROPERTIES
  @Autowired private JdbcTemplate jdbcTemplate;

  //=========================================================================================================
  // DELETE
  //=========================================================================================================
  public int[] delete(List<PersonDTO> records) {

    return jdbcTemplate.batchUpdate(

      "DELETE FROM PERSON WHERE NAME = ? AND AGE = ?",            //Order of parameters is used

      new BatchPreparedStatementSetter() {

        @Override
        public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
          preparedStatement.setString(1, records.get(i).getName());
          preparedStatement.setLong  (2, records.get(i).getAge ());
        }

        @Override
        public int getBatchSize() {
          return records.size();
        }

      }

    );

  }

  //=========================================================================================================
  // DELETE WITH BATCH SIZE
  //=========================================================================================================
  public int[][] deleteWithBatchSize(List<PersonDTO> records) {

    return jdbcTemplate.batchUpdate(
      "DELETE FROM PERSON WHERE NAME = ? AND AGE = ?",
      records,
      2,    //Batch size
      (PreparedStatement preparedStatement, PersonDTO record) -> {
          preparedStatement.setString(1, record.getName());
          preparedStatement.setLong  (2, record.getAge ());
      }
    );

  }

}
