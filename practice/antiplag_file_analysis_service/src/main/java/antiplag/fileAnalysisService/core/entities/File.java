package antiplag.fileAnalysisService.core.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class File {
    private final Integer id;
    private final String name;
    private final String body;
}
