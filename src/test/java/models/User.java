package models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@EqualsAndHashCode
public class User {

    @JacksonXmlProperty(localName = "name")
    private String name;

    public User(String name){
        this.name = name;
    }

    public User(){
    }


}
