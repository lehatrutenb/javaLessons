package antiplag.fileAnalysisService.core.entities;

import antiplag.fileAnalysisService.core.entities.enums.ImageFormat;
import com.databricks.client.jdbc42.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.primitives.Bytes;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="wordsCloud")
@Getter
@Setter
public class WordCloud {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="fileId", nullable = false, unique = true) // no references to other tables cause they are not in our area (another service)
    private Integer fileId;

    @Column(name="imageFormat", nullable = false)
    private ImageFormat imageFormat;

    @Column(name="image", nullable = false, length = 1000000)
    private String img;

    @Transient
    private ObjectMapper mapper = new ObjectMapper();

    public List<Byte> getImg() throws JsonProcessingException {
        return mapper.readValue(img,  new TypeReference<List<Byte>>() {});
    }

    public WordCloud(Integer fileId, ImageFormat imageFormat, List<Byte> img) {
        this.fileId = fileId;
        this.imageFormat = imageFormat;
        this.img = img.toString();
    }
}
