package antiplag.fileStoringService.adapters.in_ports.grpc;

import antiplag.fileStoringService.grpc.*;
import com.google.protobuf.NullValue;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FileMappingGrpc {
    public FileMetaResponse getFileMetaResponse(antiplag.fileStoringService.use_cases.dtos.entities.FileMetaResponse fileMetaResponse) {
        return FileMetaResponse.newBuilder()
                .setId(fileMetaResponse.id())
                .setName(fileMetaResponse.fileName())
                .setDateCreated(fileMetaResponse.dateCreated())
                .build();
    }

    public FileDataResponse getFileDataResponse(Optional<antiplag.fileStoringService.use_cases.dtos.entities.FileResponse> fileResponse) {
        if (fileResponse.isEmpty()) {
            return FileDataResponse.newBuilder().setNull(NullValue.NULL_VALUE).build();
        }
        var response = fileResponse.get();
        return FileDataResponse.newBuilder().setFile(
                FileResponse.newBuilder()
                .setMetaData(getFileMetaResponse(response.meta()))
                .setData(response.FileBody())
                .build()
        ).build();
    }

    public antiplag.fileStoringService.use_cases.dtos.entities.FileLoadRequest convertToFileLoadRequest(FileLoadRequest fileloadRequest) {
        return new antiplag.fileStoringService.use_cases.dtos.entities.FileLoadRequest(fileloadRequest.getId());
    }

    public antiplag.fileStoringService.use_cases.dtos.entities.FileStoreRequest convertToFileStoreRequest(FileStoreRequest fileStoreRequest) {
        return new antiplag.fileStoringService.use_cases.dtos.entities.FileStoreRequest(
                fileStoreRequest.getName(), fileStoreRequest.getData()
        );
    }

    private FileLoadRequest getFileLoadRequest(antiplag.fileStoringService.use_cases.dtos.entities.FileLoadRequest request) {
        return FileLoadRequest.newBuilder().setId(request.id()).build();
    }

    public StoringFilesResponse getStoringFilesResponse(List<antiplag.fileStoringService.use_cases.dtos.entities.FileLoadRequest> files) {
        var response = StoringFilesResponse.newBuilder();
        response.addAllFiles(files.stream().map(this::getFileLoadRequest).toList());
        return StoringFilesResponse.newBuilder().addAllFiles(
                files.stream().map(this::getFileLoadRequest).toList())
                .build();
    }
}
