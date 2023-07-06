package global.goit.dto;

import java.time.LocalDate;

public class YoungestAndOldest {
    private String type;
    private String name;
    private LocalDate birthday;

    public YoungestAndOldest(String type, String name, LocalDate birthday) {
        this.type = type;
        this.name = name;
        this.birthday = birthday;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "YoungestAndOldest{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
