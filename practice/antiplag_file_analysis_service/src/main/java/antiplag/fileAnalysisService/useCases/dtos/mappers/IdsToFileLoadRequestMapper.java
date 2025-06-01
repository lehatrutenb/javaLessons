package antiplag.fileAnalysisService.useCases.dtos.mappers;

import antiplag.fileAnalysisService.useCases.dtos.requests.FileLoadRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IdsToFileLoadRequestMapper {
    public FileLoadRequest getFileLoadRequest(Integer id) {
       return new FileLoadRequest(id);
    }
    public List<FileLoadRequest> getFileLoadRequests(List<Integer> ids) {
        return ids.stream().map(this::getFileLoadRequest).toList();
    }
}
