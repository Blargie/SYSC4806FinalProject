package project.examples;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BuddyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String buddyName;
    private String buddyPhoneNumber;

    public BuddyInfo() {}
    public BuddyInfo(String name, String phoneNumber) {
        this.buddyName = name;
        this.buddyPhoneNumber = phoneNumber;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setbuddyName(String name) {
        this.buddyName = name;
    }
    public void setBuddyPhoneNumber(String phoneNumber) {
        this.buddyPhoneNumber = phoneNumber;
    }
    public Integer getId() { return id; }
    public String getBuddyName() {
        return buddyName;
    }
    public String getBuddyPhoneNumber() {
        return buddyPhoneNumber;
    }

    @Override
    public String toString() {
        return "project.examples.BuddyInfo{" +
                "id=" + id +
                ", buddyName='" + buddyName + '\'' +
                ", buddyPhoneNumber='" + buddyPhoneNumber + '\'' +
                '}';
    }
}
