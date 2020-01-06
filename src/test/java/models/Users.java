package models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;

@Data
@Getter
@Setter
@JacksonXmlRootElement(localName = "users")
public class Users {

    @JacksonXmlElementWrapper(localName = "user", useWrapping = false)
    HashSet<User> user;

    public HashSet<User> getUser() {
        return user;
    }

    public void setUser(HashSet<User> user) {
        this.user = user;
    }

    public Users() {
        this.user = new HashSet<>();
    }

    public Users(HashSet<User> users) {
        this.user = users;
    }

    public void addUsers(HashSet<String> userNames, Date date) {
        HashSet<User> users = new HashSet<>();
        for (String name : userNames) {
            users.add(new User(name, date));
        }
        this.user.addAll(users);
    }
}
