package ee.lostpearls.persistence.county;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "county", schema = "pearls")
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotNull
    @Column(name = "longfield", nullable = false, precision = 9, scale = 6)
    private BigDecimal longfield;

    @NotNull
    @Column(name = "latfield", nullable = false, precision = 9, scale = 6)
    private BigDecimal latfield;

    @NotNull
    @Column(name = "zoom_level", nullable = false)
    private Integer zoomLevel;
}