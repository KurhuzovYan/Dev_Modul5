package global.goit.dto;

public class MaxSalary {
    private String name;
    private int salary;

    public MaxSalary(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "MaxSalary{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
