package lk.ijse.greenshadowbackend.Controller;

import lk.ijse.greenshadowbackend.Dto.Impl.CropDto;
import lk.ijse.greenshadowbackend.Exception.DataPersistException;
import lk.ijse.greenshadowbackend.Service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

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
}
