package models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "students_tbl")
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fld")
    private Long id;
    @Column(name = "name_fld")
    private String name;
    @Column(name = "mark_fld")
    private int mark;

    public Student(String name, int mark) {
        this.name = name;
        this.mark = mark;
    }
}
