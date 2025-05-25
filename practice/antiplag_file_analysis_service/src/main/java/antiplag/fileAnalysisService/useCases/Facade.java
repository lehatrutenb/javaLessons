package antiplag.fileAnalysisService.useCases;

import antiplag.fileAnalysisService.useCases.dtos.mappers.FileAnalysationMapper;
import antiplag.fileAnalysisService.useCases.dtos.mappers.WordCloudInMapper;
import antiplag.fileAnalysisService.useCases.dtos.requests.FileAnalyseRequest;
import antiplag.fileAnalysisService.useCases.dtos.requests.GetWordCloudInRequest;
import antiplag.fileAnalysisService.useCases.dtos.responses.FileAnalyseResponse;
import antiplag.fileAnalysisService.useCases.dtos.responses.GetWordCloudInResponse;
import antiplag.fileAnalysisService.useCases.interfaces.IfilesLoadInnerOutController;
import antiplag.fileAnalysisService.useCases.services.FileAnalyseService;
import antiplag.fileAnalysisService.useCases.services.WordCloudService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Facade implements Ifacade {
    private final WordCloudService wordCloudService;
    private final FileAnalyseService fileAnalyseService;
    private final IfilesLoadInnerOutController filesLoadInnerOutController;
    private final WordCloudInMapper wordCloudInMapper;
    private final FileAnalysationMapper fileAnalysationMapper;

    @Override
    public GetWordCloudInResponse getWordCloud(GetWordCloudInRequest request) {
        var file = filesLoadInnerOutController.loadFiles(List.of(request.id())).getFirst();
        try {
            return wordCloudInMapper.wordCloudToWordCloudInResponse(
                    wordCloudService.getWordCloud(file.orElseThrow())
                    );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileAnalyseResponse analyseFile(FileAnalyseRequest request) {
        return fileAnalysationMapper.getFileAnalyseResponse(
                fileAnalyseService.AnalyseFile(request.id(), request.forceRecalc())
        );
    }
}
