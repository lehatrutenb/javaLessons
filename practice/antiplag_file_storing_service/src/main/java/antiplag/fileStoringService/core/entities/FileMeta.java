package antiplag.fileStoringService.core.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="files_meta")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime dateCreated;

    public FileMeta(String name, LocalDateTime dateCreated) {
        this.name = name;
        this.dateCreated = dateCreated;
    }
}
