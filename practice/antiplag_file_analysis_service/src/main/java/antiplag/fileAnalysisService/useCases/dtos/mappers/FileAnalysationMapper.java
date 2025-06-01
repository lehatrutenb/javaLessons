package antiplag.fileAnalysisService.useCases.dtos.mappers;

import antiplag.fileAnalysisService.core.entities.AnalysationResult;
import antiplag.fileAnalysisService.useCases.dtos.responses.FileAnalyseResponse;
import antiplag.fileAnalysisService.useCases.dtos.responses.FileClosenessResponse;
import org.springframework.stereotype.Component;

@Component
public class FileAnalysationMapper {
    public FileAnalyseResponse getFileAnalyseResponse(AnalysationResult analysationResult) {
        return new FileAnalyseResponse(
                analysationResult.getNearestFiles().stream().map(
                        fileCloseness -> new FileClosenessResponse(
                                fileCloseness.file().getId(),
                                fileCloseness.file().getName(),
                                fileCloseness.rank()
                        )
                ).toList()
        );
    }
}
