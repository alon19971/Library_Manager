package library;

/**
 * Class representing a member of the library.
 */
public class Member {
    private String name;
    private int id;

    /**
     * Constructor to initialize a member.
     *
     * @param name the name of the member
     * @param id the ID of the member
     */
    public Member(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
