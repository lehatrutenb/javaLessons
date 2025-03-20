package hse.kpo.interfaces;

import hse.kpo.domains.DurationReport;
import hse.kpo.enums.DataType;

public interface ReportExporterFactoryI<T> {
    public ExporterI<T> create(DataType dataType);
}
