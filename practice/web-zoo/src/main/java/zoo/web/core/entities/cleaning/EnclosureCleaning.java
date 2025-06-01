package zoo.web.core.entities.cleaning;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class EnclosureCleaning {
    @Getter
    private final UUID id;

    public void clean() {

    }
}
