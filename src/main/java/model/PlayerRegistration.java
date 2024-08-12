package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRegistration {

    private Integer id;
    private String fullName;
    private String age;
    private String gender;
    private String phoneNo;
    private String email;
    private String currentlyTraining;
    private String training;
    private String other;
    private String date;
    private String address;


}
