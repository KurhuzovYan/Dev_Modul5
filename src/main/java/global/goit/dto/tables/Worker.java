package global.goit.dto.tables;

import java.util.ArrayList;
import java.util.List;

import static global.goit.util.Extracter.getStringBuilder;
import static global.goit.util.Reader.getStringFromSQL;

public class Worker {

    private String name;
    private java.sql.Date birthday;
    private String level;
    private Integer salary;

    public Worker(String name, java.sql.Date birthday, String level, Integer salary) {
        this.name = name;
        this.birthday = birthday;
        this.level = level;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public java.sql.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public static List<Worker> getWorkers() {
        String stringFromSQL = getStringFromSQL("sql/populate_db.sql");
        StringBuilder workerBuilder = getStringBuilder(stringFromSQL);
        List<Worker> workers = new ArrayList<>();
        for (String s : workerBuilder.toString().split("\n")) {
            if (!s.equals("VALUES")) {
                String substring = s.substring(s.indexOf("(") + 1, s.indexOf(")"));
                String[] split = substring.replaceAll("[\' ]", "").split(",");
                if (!split[0].equals("name")) {
                    workers.add(new Worker(split[0], java.sql.Date.valueOf(split[1]), split[2], Integer.valueOf(split[3])));
                }
            }
        }
        return workers;
    }

}
