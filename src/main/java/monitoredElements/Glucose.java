package monitoredElements;

import hibernate.HibernateEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "GlucoseMeasurements")
@ToString
@RequiredArgsConstructor


public class Glucose implements HibernateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Getter @Setter
    private Integer id;

    @Column(name = "Date")
    @Getter @Setter
    private String date;

    @Column(name = "Glucose")
    @Getter @Setter
    private double glucose;

    @Column (name ="State")
    @Getter @Setter
    private MesurmentState mesurmentStates;


}

