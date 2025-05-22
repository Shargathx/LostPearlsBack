package ee.lostpearls.persistence.location;

import ee.lostpearls.persistence.county.County;
import ee.lostpearls.persistence.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "location", schema = "pearls")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "county_id", nullable = false)
    private County county;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String locationName;

    @Column(name = "long", precision = 18, scale = 15)
    private BigDecimal longField;

    @Column(name = "lat", precision = 18, scale = 15)
    private BigDecimal lat;

    @NotNull
    @Column(name = "status", nullable = false, length = Integer.MAX_VALUE)
    private String status;

    @Size(max = 1000)
    @NotNull
    @Column(name = "teaser", nullable = false, length = 1000)
    private String teaser;

    @Size(max = 2000)
    @NotNull
    @Column(name = "extended_info", nullable = false, length = 2000)
    private String extendedInfo;

    @Size(max = 500)
    @NotNull
    @Column(name = "question", nullable = false, length = 500)
    private String question;

    @Size(max = 500)
    @NotNull
    @Column(name = "answer", nullable = false, length = 500)
    private String answer;

    @NotNull
    @Column(name = "date_added", nullable = false)
    private LocalDate dateAdded;

}