package lk.ijse.greenshadowbackend.Service;

import lk.ijse.greenshadowbackend.Dto.Impl.LogDto;
import lk.ijse.greenshadowbackend.Dto.LogStatus;
import lk.ijse.greenshadowbackend.Entity.Impl.LogEntity;

import java.util.List;

public interface LogService {
    void saveLog(LogDto logDto);

    LogStatus getLogById(String id);

    List<LogDto> getAllLog();

    void updateLog(String id, LogDto logDto);

    void deleteLog(String id);
}
