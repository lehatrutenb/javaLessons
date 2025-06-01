package antiplag.fileAnalysisService.core.entities;

import jakarta.persistence.Entity;

public record FileCloseness(
    File file,
    int rank
) { }
