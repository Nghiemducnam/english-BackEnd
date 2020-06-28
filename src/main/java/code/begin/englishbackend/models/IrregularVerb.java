package code.begin.englishbackend.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "IrregularVerb")
public class IrregularVerb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String verb;
    private String verb1;
    private String verb2;
    private String meaning;
    private String spellingVerb;
    private String spellingVerb1;
    private String spellingVerb2;
    private Integer verbCategory;
}
