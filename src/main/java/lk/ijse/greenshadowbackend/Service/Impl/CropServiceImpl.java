package lk.ijse.greenshadowbackend.Service.Impl;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.CropStatus;
import lk.ijse.greenshadowbackend.Dto.Impl.CropDto;
import lk.ijse.greenshadowbackend.Entity.Impl.CropEntity;
import lk.ijse.greenshadowbackend.Entity.Impl.FieldEntity;
import lk.ijse.greenshadowbackend.Exception.CropNotFoundException;
import lk.ijse.greenshadowbackend.Repository.CropRepo;
import lk.ijse.greenshadowbackend.Repository.FieldRepo;
import lk.ijse.greenshadowbackend.Service.CropService;
import lk.ijse.greenshadowbackend.Util.AppUtil;
import lk.ijse.greenshadowbackend.Util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private CropRepo cropRepo;
    @Autowired
    private FieldRepo fieldRepo;
    @Autowired
    private Mapping mapper;

    @Override
    public void saveCrop(CropDto cropDto) {
        int number = 0;
        CropEntity crop = cropRepo.findLastRowNative();
        if (crop != null) {
            String[] parts = crop.getCropId().split("-");
            number = Integer.parseInt(parts[1]);
        }
        cropDto.setCropId("CROP-" + (number + 1));
        crop = mapper.toCropEntity(cropDto);
        cropRepo.save(crop);
    }

    @Override
    public CropStatus getCropById(String cropId) {
        if (cropRepo.existsById(cropId)) {
            CropEntity referenceById = cropRepo.getReferenceById(cropId);

            CropDto cropDto = new CropDto();
            cropDto.setCropId(referenceById.getCropId());
            cropDto.setCommonName(referenceById.getCommonName());
            cropDto.setScientificName(referenceById.getScientificName());
            cropDto.setCropImg(referenceById.getCropImg());
            cropDto.setCategory(referenceById.getCategory());
            cropDto.setSeason(referenceById.getSeason());
            cropDto.setField(referenceById.getField().getFieldId());

            return cropDto;
        } else {
            return new SelectedErrorStatusCode(2, "Crop does not exist!!");
        }
    }

    @Override
    public List<CropDto> getAllCrops() {
        List<CropEntity> cropEntities = cropRepo.findAll();
        List<CropDto> cropDtos = new ArrayList<>();

        for (CropEntity cropEntity : cropEntities) {
            CropDto cropDto = new CropDto();
            cropDto.setCropId(cropEntity.getCropId());
            cropDto.setCommonName(cropEntity.getCommonName());
            cropDto.setScientificName(cropEntity.getScientificName());
            cropDto.setCropImg(cropEntity.getCropImg());
            cropDto.setCategory(cropEntity.getCategory());
            cropDto.setSeason(cropEntity.getSeason());
            cropDto.setField(cropEntity.getField().getFieldId());
            cropDtos.add(cropDto);
        }

        return cropDtos;
    }


    @Override
    public void deleteCrop(String cropId) {
        Optional<CropEntity> byId = cropRepo.findById(cropId);
        if (!byId.isPresent()) {
            throw new CropNotFoundException("crop not found!!");
        } else {
            cropRepo.deleteById(cropId);
        }
    }

    @Override
    public void updateCrop(String cropId, CropDto cropDto) {
        Optional<CropEntity> cropOptional = cropRepo.findById(cropId);

        if (!cropOptional.isPresent()) {
            throw new CropNotFoundException("Crop not found!");
        }

        CropEntity cropEntity = cropOptional.get();
        FieldEntity fieldEntity = fieldRepo.findById(cropDto.getField())
                .orElseThrow(() -> new RuntimeException("Field not found with ID: " + cropDto.getField()));

        cropEntity.setCommonName(cropDto.getCommonName());
        cropEntity.setScientificName(cropDto.getScientificName());
        cropEntity.setCropImg(cropDto.getCropImg());
        cropEntity.setCategory(cropDto.getCategory());
        cropEntity.setSeason(cropDto.getSeason());
        cropEntity.setField(fieldEntity);
    }

}