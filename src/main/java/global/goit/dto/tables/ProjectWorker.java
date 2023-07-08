package global.goit.dto.tables;


import java.util.ArrayList;
import java.util.List;

import static global.goit.util.Extracter.getStringBuilder;
import static global.goit.util.Reader.getStringFromSQL;

public class ProjectWorker {

  private Integer projectId;
  private Integer workerId;

  public ProjectWorker(Integer projectId, Integer workerId) {
    this.projectId = projectId;
    this.workerId = workerId;
  }

  public Integer getProjectId() {
    return projectId;
  }

  public void setProjectId(Integer projectId) {
    this.projectId = projectId;
  }


  public Integer getWorkerId() {
    return workerId;
  }

  public void setWorkerId(Integer workerId) {
    this.workerId = workerId;
  }

  public static List<ProjectWorker> getProjectWorkers() {
    String stringFromSQL = getStringFromSQL("sql/populate_db.sql");
    StringBuilder workerBuilder = getStringBuilder(stringFromSQL);
    String clientInsert = stringFromSQL.replace(workerBuilder.toString(), "");
    StringBuilder clientBuilder = getStringBuilder(clientInsert);
    String projectInsert = clientInsert.replace(clientBuilder.toString(), "");
    StringBuilder projectBuilder = getStringBuilder(projectInsert);
    String projectWorkerInsert = projectInsert.replace(projectBuilder.toString(), "");
    StringBuilder projectWorkerBuilder = getStringBuilder(projectWorkerInsert);
    List<ProjectWorker> projectWorkerList = new ArrayList<>();
    for (String s : projectWorkerBuilder.toString().trim().split("\n")) {
      if (!s.equals("VALUES")) {
        String substring3 = s.substring(s.indexOf("(") + 1, s.indexOf(")"));
        String[] split = substring3.replaceAll("[\' ]", "").split(",");
        if (!split[0].equals("project_id")) {
          projectWorkerList.add(new ProjectWorker(Integer.valueOf(split[0]), Integer.valueOf(split[1])));
        }
      }
    }
    return projectWorkerList;
  }

}
