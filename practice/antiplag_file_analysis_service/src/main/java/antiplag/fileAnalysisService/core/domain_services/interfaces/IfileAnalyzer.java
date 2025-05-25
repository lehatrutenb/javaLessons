package antiplag.fileAnalysisService.core.domain_services.interfaces;

import antiplag.fileAnalysisService.core.entities.AnalysationResult;
import antiplag.fileAnalysisService.core.entities.File;

import java.util.List;

public interface IfileAnalyzer {
    public AnalysationResult getClosestFiles(File f1, List<File> files);
}
