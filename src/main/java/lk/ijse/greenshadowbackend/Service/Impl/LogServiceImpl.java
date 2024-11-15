package lk.ijse.greenshadowbackend.Service.Impl;

import lk.ijse.greenshadowbackend.Dto.Impl.LogDto;
import lk.ijse.greenshadowbackend.Entity.Impl.LogEntity;
import lk.ijse.greenshadowbackend.Repository.LogRepo;
import lk.ijse.greenshadowbackend.Service.LogService;
import lk.ijse.greenshadowbackend.Util.AppUtil;
import lk.ijse.greenshadowbackend.Util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
