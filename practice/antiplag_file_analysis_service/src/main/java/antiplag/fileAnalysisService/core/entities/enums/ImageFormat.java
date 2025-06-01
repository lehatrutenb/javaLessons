package antiplag.fileAnalysisService.core.entities.enums;

import java.util.Arrays;
import java.util.Optional;

public enum ImageFormat {
    PNG ("PNG"),
    JPG ("JPG");

    private final String type;

    ImageFormat(String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }

    public static Optional<ImageFormat> get(String typeS) {
        return Arrays.stream(values()).filter(type -> type.type.equals(typeS)).findFirst();
    }
}
