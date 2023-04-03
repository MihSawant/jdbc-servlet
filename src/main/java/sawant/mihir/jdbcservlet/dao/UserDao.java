package sawant.mihir.jdbcservlet.dao;

public class UserDao {
    private String name;
    private String designation;

    public String getName() {
        return name;
    }

    public UserDao(){};

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "UserDao{" +
                "name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}
