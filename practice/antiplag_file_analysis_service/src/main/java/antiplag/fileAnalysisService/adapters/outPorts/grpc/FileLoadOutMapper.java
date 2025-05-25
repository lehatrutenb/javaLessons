package antiplag.fileAnalysisService.adapters.outPorts.grpc;

import antiplag.fileStoringService.grpc.FileDataResponse;
import antiplag.fileStoringService.grpc.FileLoadRequest;
import antiplag.fileAnalysisService.useCases.dtos.responses.FileBodyResponse;
import antiplag.fileAnalysisService.useCases.dtos.responses.FileMetaResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class FileLoadOutMapper {
    public FileMetaResponse getFileMetaResponse(antiplag.fileStoringService.grpc.FileMetaResponse response) {
        return new FileMetaResponse(
                response.getId(),
                response.getName(),
                response.getDateCreated()
        );
    }

    public Optional<FileBodyResponse> FileDataResponseToFileBodyResponse(FileDataResponse fileDataResponse) {
        if (fileDataResponse.hasNull()) {
            return Optional.empty();
        }
        return Optional.of(
                new FileBodyResponse(getFileMetaResponse(fileDataResponse.getFile().getMetaData()), fileDataResponse.getFile().getData())
        );
    }

    public FileLoadRequest getFileLoadRequest(antiplag.fileAnalysisService.useCases.dtos.requests.FileLoadRequest fileLoadRequest) {
        return FileLoadRequest.newBuilder().setId(fileLoadRequest.id()).build();
    }

    public antiplag.fileAnalysisService.useCases.dtos.requests.FileLoadRequest getFileLoadRequest(FileLoadRequest fileLoadRequest) {
        return new antiplag.fileAnalysisService.useCases.dtos.requests.FileLoadRequest(fileLoadRequest.getId());
    }
}
