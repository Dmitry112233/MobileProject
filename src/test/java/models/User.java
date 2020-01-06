package models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Getter
@Setter
@EqualsAndHashCode
public class User {

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "date")
    private String date;

    public User(String name, Date date){
        this.name = name;
        this.date = formatDateToString(date);
    }

    public User(){
    }

    public void setDateFormated(Date date){
        this.date = formatDateToString(date);
    }

    private String formatDateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }
}
