package lk.ijse.greenshadowbackend.Service.Impl;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.Impl.LogDto;
import lk.ijse.greenshadowbackend.Dto.LogStatus;
import lk.ijse.greenshadowbackend.Entity.Impl.LogEntity;
import lk.ijse.greenshadowbackend.Repository.LogRepo;
import lk.ijse.greenshadowbackend.Service.LogService;
import lk.ijse.greenshadowbackend.Util.AppUtil;
import lk.ijse.greenshadowbackend.Util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogRepo logRepo;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveLog(LogDto logDto) {
        logDto.setId(AppUtil.generateLogId());
        LogEntity save = logRepo.save(mapping.toMonitoringLogEntity(logDto));
        if (save==null){
            throw new RuntimeException("Log Save Failed");
        }
    }

    @Override
    public LogStatus getLogById(String id) {
        if (logRepo.existsById(id)){
            LogEntity referenceById = logRepo.getReferenceById(id);
            return mapping.toMonitoringLogDTO(referenceById);
        }
        return new SelectedErrorStatusCode(2,"Log Not Found");
    }

    @Override
    public List<LogDto> getAllLog() {
        return mapping.toMonitoringLogDTOList(logRepo.findAll());
    }

    @Override
    public void updateLog(String id, LogDto logDto) {

        Optional<LogEntity> byId = logRepo.findById(id);
        if (!byId.isPresent()){
            throw new RuntimeException("Log not found");
        }else {
            byId.get().setLog_date(logDto.getLog_date());
            byId.get().setLog_details(logDto.getLog_details());
            byId.get().setObserved_image(logDto.getObserved_image());
        }
    }

    @Override
    public void deleteLog(String id) {
        Optional<LogEntity> byId = logRepo.findById(id);
        if (!byId.isPresent()){
            throw new RuntimeException("Log not found");
        }else {
            logRepo.deleteById(id);
        }
    }
}
