package project.examples;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<BuddyInfo> buddyList = new ArrayList<BuddyInfo>();
    public AddressBook() {}
    public void addBuddy(BuddyInfo buddy) {
        buddyList.add(buddy);
    }
    public BuddyInfo removeBuddy(int index) {
        if (index >= 0 && index < buddyList.size()) {
            return buddyList.remove(index);
        } else {
            return null;
        }
    }

    public BuddyInfo removeBuddy(BuddyInfo buddy) {
        if (buddyList.contains(buddy)) {
            buddyList.remove(buddy);
            return buddy;
        } else {
            return null;
        }
    }
    public BuddyInfo getBuddy(int index) {
        if (index >= 0 && index < buddyList.size()) {
            return buddyList.get(index);
        } else {
            return null;
        }
    }


    public List<BuddyInfo> getBuddyList() {
        return buddyList;
    }
    public void setBuddyList(List<BuddyInfo> buddyList) {
        this.buddyList = buddyList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        String buddyList = "project.examples.AddressBook{" + "id=" + id + ", buddyList=[";
        for (BuddyInfo buddy : this.buddyList) {
            buddyList += buddy.toString() + ", ";
        }
        buddyList += "]}";
        return buddyList;
    }
}

