package antiplag.fileAnalysisService.core.domain_services.factories;

import antiplag.fileAnalysisService.core.entities.File;
import org.springframework.stereotype.Component;

@Component
public class FileFactory {
    public File createFile(Integer id, String name, String body) {
        return new File(id, name, body);
    }
}
