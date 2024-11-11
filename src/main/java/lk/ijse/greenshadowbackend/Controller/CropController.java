package lk.ijse.greenshadowbackend.Controller;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.CropStatus;
import lk.ijse.greenshadowbackend.Dto.Impl.CropDto;
import lk.ijse.greenshadowbackend.Exception.DataPersistException;
import lk.ijse.greenshadowbackend.Service.CropService;
import lk.ijse.greenshadowbackend.Util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("api/v1/crop")
public class CropController {
    @Autowired
    private CropService cropService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>saveCrop(
            @RequestPart("commonName") String commonName,
            @RequestPart("scientificName") String scientificName,
            @RequestPart("cropImg") MultipartFile cropImg,
            @RequestPart("category") String category,
            @RequestPart("season") String season
    ){
        String cropBase64 = "";

        try {
            byte[] byteCropImg = cropImg.getBytes();
            cropBase64 = Base64.getEncoder().encodeToString(byteCropImg);

            CropDto cropDto = new CropDto();

            cropDto.setCommonName(commonName);
            cropDto.setScientificName(scientificName);
            cropDto.setCropImg(cropBase64);
            cropDto.setCategory(category);
            cropDto.setSeason(season);

            cropService.saveCrop(cropDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{cropId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CropStatus getCropById(@PathVariable ("cropId") String cropId){
       if (!Regex.cropCodeMatcher(cropId)){
           return new SelectedErrorStatusCode(1,"Invalid Crop Id");
       }
       return cropService.getCropById(cropId);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDto>getAllCrops(){
        return cropService.getAllCrops();
    }
}
