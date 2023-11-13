import java.io.Serializable;
import java.util.StringJoiner;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private String id;
    private double cost;

    public Product(String name, String description, String id, double cost) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public double getCost() {
        return cost;
    }

    // Utility method for formatting records for RandomAccessFile
    public String formatForRandomAccess() {
        return String.format("%-35s%-75s%-6s%-8.2f", name, description, id, cost);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("id='" + id + "'")
                .add("cost=" + cost)
                .toString();
    }
}
