package global.goit.dto;

public class ProjectPrices {
    private int id;
    private int cost;

    public ProjectPrices(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "ProjectPrices{" +
                "id=" + id +
                ", cost=" + cost +
                '}';
    }
}
