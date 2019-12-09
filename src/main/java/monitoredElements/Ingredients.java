package monitoredElements;



import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Ingredients")
@ToString
@RequiredArgsConstructor
public class Ingredients {
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

    @Column(name = "Protein")
    @Getter
    @Setter
    @NonNull
    private double protein;

    @Column(name = "Fat")
    @Getter
    @Setter
    @NonNull
    private double fat;

    @Column(name = "Carbohydrate")
    @Getter
    @Setter
    @NonNull
    private double carbohydrate;

    @Column(name = "Monosaccharides")
    @Getter
    @Setter
    @NonNull
    private double monosaccarides;

    @Column(name = "S_O_R_Weight")
    @Getter
    @Setter
    @NonNull
    private double scaleOfReferenceWeight;

    @Column(name = "Kcal")
    @Getter
    @Setter
    @NonNull
    private double kcal;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Meal_ID", nullable = false, referencedColumnName = "ID")
    @Getter @Setter
    @NonNull
    public Meals meal;


    public Ingredients() {

    }
}
