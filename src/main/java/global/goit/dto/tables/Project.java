package global.goit.dto.tables;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static global.goit.util.Extracter.getStringBuilder;
import static global.goit.util.Reader.getStringFromSQL;

public class Project {

  private Integer clientId;
  private java.sql.Date startDate;
  private java.sql.Date finishDate;

  public Project(Integer clientId, Date startDate, Date finishDate) {
    this.clientId = clientId;
    this.startDate = startDate;
    this.finishDate = finishDate;
  }

  public Integer getClientId() {
    return clientId;
  }

  public void setClientId(Integer clientId) {
    this.clientId = clientId;
  }


  public java.sql.Date getStartDate() {
    return startDate;
  }

  public void setStartDate(java.sql.Date startDate) {
    this.startDate = startDate;
  }


  public java.sql.Date getFinishDate() {
    return finishDate;
  }

  public void setFinishDate(java.sql.Date finishDate) {
    this.finishDate = finishDate;
  }

  public static List<Project> getProjects() {
    String stringFromSQL = getStringFromSQL("sql/populate_db.sql");
    StringBuilder workerBuilder = getStringBuilder(stringFromSQL);
    String clientInsert = stringFromSQL.replace(workerBuilder.toString(), "");
    StringBuilder clientBuilder = getStringBuilder(clientInsert);
    String projectInsert = clientInsert.replace(clientBuilder.toString(), "");
    StringBuilder projectBuilder = getStringBuilder(projectInsert);
    List<Project> projects = new ArrayList<>();
    for (String s : projectBuilder.toString().trim().split("\n")) {
      if (!s.equals("VALUES")) {
        String substring2 = s.substring(s.indexOf("(") + 1, s.indexOf(")"));
        String[] split = substring2.replaceAll("[\' ]", "").split(",");
        if (!split[0].equals("client_id")) {
          projects.add(new Project(Integer.valueOf(split[0]), java.sql.Date.valueOf(split[1]), java.sql.Date.valueOf(split[2])));
        }
      }
    }
    return projects;
  }

}
