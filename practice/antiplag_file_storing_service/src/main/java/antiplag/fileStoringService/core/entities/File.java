package antiplag.fileStoringService.core.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "files")
public class File {
    @Id
    private Integer id;

    @Column(nullable = false, length = 10100)
    private String body;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    @MapsId
    private FileMeta meta;

    public File(String body, FileMeta meta) {
        if (meta.getId() != null) {
            id = meta.getId();
        }
        this.body = body;
        this.meta = meta;
    }
}
