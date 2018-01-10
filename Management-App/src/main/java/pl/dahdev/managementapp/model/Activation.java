package pl.dahdev.managementapp.model;

import javax.persistence.*;

@Entity
public class Activation {

    public Activation() {
    }

    public Activation(String activationCode, User user) {
        this.activationCode = activationCode;
        this.user = user;
    }

    @Id
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "ACTIVATION_CODE", nullable = false, unique = true)
    private String activationCode;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activation that = (Activation) o;

        if (id != that.id) return false;
        if (activationCode != null ? !activationCode.equals(that.activationCode) : that.activationCode != null)
            return false;
        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (activationCode != null ? activationCode.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Activation{" +
                "id=" + id +
                ", activationCode='" + activationCode + '\'' +
                ", user=" + user +
                '}';
    }
}
