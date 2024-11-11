package lk.ijse.greenshadowbackend.Service.Impl;

import lk.ijse.greenshadowbackend.Dto.Impl.CropDto;
import lk.ijse.greenshadowbackend.Entity.Impl.CropEntity;
import lk.ijse.greenshadowbackend.Repository.CropRepo;
import lk.ijse.greenshadowbackend.Service.CropService;
import lk.ijse.greenshadowbackend.Util.AppUtil;
import lk.ijse.greenshadowbackend.Util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private CropRepo cropRepo;
    @Autowired
    private Mapping mapper;
    @Override
    public void saveCrop(CropDto cropDto) {
        cropDto.setCropId(AppUtil.generateCropId());
        CropEntity save = cropRepo.save(mapper.toCropEntity(cropDto));
        if (save==null){
            throw new RuntimeException("Crop Save Failed");
        }
    }
}
