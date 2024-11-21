package lk.ijse.greenshadowbackend.Util;

import lk.ijse.greenshadowbackend.Dto.Impl.*;
import lk.ijse.greenshadowbackend.Entity.Impl.*;
import lk.ijse.greenshadowbackend.Repository.EquipmentRepo;
import lk.ijse.greenshadowbackend.Repository.FieldRepo;
import lk.ijse.greenshadowbackend.Repository.StaffRepo;
import lk.ijse.greenshadowbackend.Service.FieldService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FieldRepo fieldRepo;
    @Autowired
    private StaffRepo staffRepo;


    //for crop mapping
    public CropEntity toCropEntity(CropDto cropDTO) {
        FieldEntity fieldEntity = fieldRepo.findById(cropDTO.getField())
                .orElseThrow(() -> new RuntimeException("Field not found with ID: " + cropDTO.getField()));

        return new CropEntity(
                cropDTO.getCropId(),
                cropDTO.getCommonName(),
                cropDTO.getScientificName(),
                cropDTO.getCropImg(),
                cropDTO.getCategory(),
                cropDTO.getSeason(),
                fieldEntity // Pass FieldEntity here
        );    }

    public CropDto toCropDTO(CropEntity cropEntity) {
        return modelMapper.map(cropEntity, CropDto.class);
    }

    public List<CropDto> toCropDTOList(List<CropEntity> cropEntitiesList) {
        return modelMapper.map(cropEntitiesList,new TypeToken<List<CropDto>>() {}.getType());
    }

    //for equipment mapping
    public EquipmentEntity toEquipmentEntity(EquipmentDto equipmentDTO) {
        FieldEntity fieldEntity = fieldRepo.findById(equipmentDTO.getField())
                .orElseThrow(() -> new RuntimeException("Field not found with ID: " + equipmentDTO.getField()));
        StaffEntity staffEntity = staffRepo.findById(equipmentDTO.getStaff())
                .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + equipmentDTO.getStaff()));

        return new EquipmentEntity(
                equipmentDTO.getEqId(),
                equipmentDTO.getName(),
                equipmentDTO.getEquipmentType(),
                equipmentDTO.getStatus(),
                staffEntity, // Pass StaffEntity here
                fieldEntity // Pass FieldEntity here
        );
    }
    public EquipmentDto toEquipmentDTO(EquipmentEntity equipmentEntity) {
        return modelMapper.map(equipmentEntity, EquipmentDto.class);
    }

    public List<EquipmentDto> toEquipmentDTOList(List<EquipmentEntity> equipmentEntitiesList) {
        return modelMapper.map(equipmentEntitiesList,new TypeToken<List<EquipmentDto>>() {}.getType());
    }

    //for field mapping
    public FieldEntity toFieldEntity(FieldDto fieldDTO) {
        return modelMapper.map(fieldDTO, FieldEntity.class);
    }

    public FieldDto toFieldDTO(FieldEntity fieldEntity) {
        return modelMapper.map(fieldEntity, FieldDto.class);
    }

    public List<FieldDto> toFieldDTOList(List<FieldEntity> fieldEntitiesList) {
        return modelMapper.map(fieldEntitiesList,new TypeToken<List<FieldDto>>() {}.getType());
    }

    //for log mapping
    public LogEntity toMonitoringLogEntity(LogDto monitoringLogDTO) {
        return modelMapper.map(monitoringLogDTO, LogEntity.class);
    }

    public LogDto toMonitoringLogDTO(LogEntity logEntity) {
        return modelMapper.map(logEntity, LogDto.class);
    }

    public List<LogDto> toMonitoringLogDTOList(List<LogEntity> logEntitiesList) {
        return modelMapper.map(logEntitiesList,new TypeToken<List<LogDto>>() {}.getType());
    }

    //for staff mapping
    public StaffEntity toStaffEntity(StaffDto staffDTO) {
        return modelMapper.map(staffDTO, StaffEntity.class);
    }

    public StaffDto toStaffDTO(StaffEntity staffEntity) {
        return modelMapper.map(staffEntity, StaffDto.class);
    }

    public List<StaffDto> toStaffDTOList(List<StaffEntity> staffEntitiesList) {
        return modelMapper.map(staffEntitiesList,new TypeToken<List<StaffDto>>() {}.getType());
    }

    //for user mapping
//    public UserEntity toUserEntity(UserDto userDTO) {
//        return modelMapper.map(userDTO, UserEntity.class);
//    }
//
//    public UserDto toUserDTO(UserEntity userEntity) {
//        return modelMapper.map(userEntity, UserDto.class);
//    }
//
//    public List<UserDto> toUserDTOList(List<UserEntity> userEntitiesList) {
//        return modelMapper.map(userEntitiesList,new TypeToken<List<UserDto>>() {}.getType());
//    }

    //for vehicle mapping
    public VehicleEntity toVehicleEntity(VehicleDto vehicleDTO) {
        StaffEntity staffEntity = staffRepo.findById(vehicleDTO.getStaff())
                .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + vehicleDTO.getStaff()));

        return new VehicleEntity(
                vehicleDTO.getVehicle_code(),
                vehicleDTO.getLicensePlateNumber(),
                vehicleDTO.getVehicleCategory(),
                vehicleDTO.getFuelType(),
                vehicleDTO.getStatus(),
                staffEntity // Pass StaffEntity here
        );
    }

    public VehicleDto toVehicleDTO(VehicleEntity vehicleEntity) {
        return modelMapper.map(vehicleEntity, VehicleDto.class);
    }

    public List<VehicleDto> toVehicleDTOList(List<VehicleEntity> vehicleEntitiesList) {
        return modelMapper.map(vehicleEntitiesList,new TypeToken<List<VehicleDto>>() {}.getType());
    }
}