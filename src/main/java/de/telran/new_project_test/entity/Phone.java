package de.telran.new_project_test.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Phone")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Person person;
}
