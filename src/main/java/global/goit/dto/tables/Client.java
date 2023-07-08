package global.goit.dto.tables;


import java.util.ArrayList;
import java.util.List;

import static global.goit.util.Extracter.getStringBuilder;
import static global.goit.util.Reader.getStringFromSQL;

public class Client {

  private String name;

  public Client(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static List<Client> getClients() {
    String stringFromSQL = getStringFromSQL("sql/populate_db.sql");
    StringBuilder workerBuilder = getStringBuilder(stringFromSQL);
    String clientInsert = stringFromSQL.replace(workerBuilder.toString(), "");
    StringBuilder clientBuilder = getStringBuilder(clientInsert);
    List<Client> clients = new ArrayList<>();
    for (String s : clientBuilder.toString().trim().split("\n")) {
      if (!s.equals("VALUES")) {
        String substring1 = s.substring(s.indexOf("(") + 1, s.indexOf(")"));
        String[] split = substring1.replaceAll("[\' ]", "").split(",");
        if (!split[0].equals("name")) {
          clients.add(new Client(split[0]));
        }
      }
    }
    return clients;
  }

}
