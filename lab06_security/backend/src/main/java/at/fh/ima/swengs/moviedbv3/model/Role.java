package at.fh.ima.swengs.moviedbv3.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Role {

    @Id
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    @Version
    private long version;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(String roleName, List<User> users) {
        this.roleName = roleName;
        this.users = users;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(users, role.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}
