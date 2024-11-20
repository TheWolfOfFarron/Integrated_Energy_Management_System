package ro.tuc.ds2020.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.sql.DataSourceDefinition;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Massage {
    private String nickname;
    private String username;
    private String rol;
    private String content;
    private Date timestamp;




}
