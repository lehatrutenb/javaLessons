package antiplag.fileAnalysisService.core.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "analystationResults")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnalysationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // not unique cause want to save previous and use timestamp for managing
    @Column(name="fileId", nullable = false) // no references to other trables cause they are not in our area (another service)
    private Integer fileId;

    @Column(name = "nearestFiles", columnDefinition = "jsonb")
    //@OneToMany(mappedBy = "analysationResultId", cascade = CascadeType.ALL, orphanRemoval = true)
    @JdbcTypeCode(SqlTypes.JSON)
    private List<FileCloseness> nearestFiles;

    @Column(nullable = false)
    private LocalDateTime dateCreated;

    public AnalysationResult(List<FileCloseness> nearestFiles, LocalDateTime dateCreated, Integer fileId) {
        this.nearestFiles = nearestFiles;
        this.dateCreated = dateCreated;
        this.fileId = fileId;
    }
}
