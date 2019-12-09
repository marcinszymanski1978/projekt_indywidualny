package monitoredElements;



import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Meals")
@ToString
@RequiredArgsConstructor
public class Meals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Getter
    @Setter
    private int id;


    @Column(name = "name")
    @Getter
    @Setter
    @NonNull
    private String name;

    @Column(name = "Kcal")
    @Getter
    @Setter
    @NonNull
    private double kcal;

    @OneToMany(mappedBy = "Ingredients", orphanRemoval = true, fetch = FetchType.EAGER)
    @Getter @Setter
    @ToString.Exclude
    private Set<Ingredients> ingredients;


    public Meals() {

    }
}
