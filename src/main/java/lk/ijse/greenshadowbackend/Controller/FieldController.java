package lk.ijse.greenshadowbackend.Controller;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.FieldStatus;
import lk.ijse.greenshadowbackend.Dto.Impl.FieldDto;
import lk.ijse.greenshadowbackend.Exception.DataPersistException;
import lk.ijse.greenshadowbackend.Exception.FieldNotFoundException;
import lk.ijse.greenshadowbackend.Service.FieldService;
import lk.ijse.greenshadowbackend.Util.AppUtil;
import lk.ijse.greenshadowbackend.Util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {
    @Autowired
    private FieldService fieldService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>saveField(
            @RequestPart ("fieldName")String fieldName,
            @RequestPart ("location")String location,
            @RequestPart ("extend") String extend,
            @RequestPart ("fieldImg1")MultipartFile fieldImg1,
            @RequestPart ("fieldImg2")MultipartFile fieldImg2
            ){
            String byteFieldImg1 = "";
            String byteFieldImg2 = "";

            try {
                byte[] bytesFieldImage1 = fieldImg1.getBytes();
                byteFieldImg1 = AppUtil.cropImageToBase64(bytesFieldImage1);

                byte[] bytesFieldImage2 = fieldImg2.getBytes();
                byteFieldImg2 = AppUtil.cropImageToBase64(bytesFieldImage2);

                FieldDto fieldDto = new FieldDto();

                fieldDto.setFieldName(fieldName);
                fieldDto.setLocation(location);
                fieldDto.setExtend(extend);
                fieldDto.setFieldImg1(byteFieldImg1);
                fieldDto.setFieldImg2(byteFieldImg2);

                fieldService.saveField(fieldDto);
                return new ResponseEntity<>(HttpStatus.CREATED);

            }catch (DataPersistException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @GetMapping(value = "/{fieldId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldStatus getFieldById(@PathVariable ("fieldId") String fieldId){
        if (!Regex.fieldCodeMatcher(fieldId)){
            return new SelectedErrorStatusCode(1,"Field code not matched");
        }
        return fieldService.getFieldById(fieldId);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDto> getAllField(){
        return fieldService.getAllFields();
    }
    @DeleteMapping(value = "/{fieldId}")
    public ResponseEntity<Void>DeleteField(@PathVariable ("fieldId") String fieldId){
        try {
            if (!Regex.fieldCodeMatcher(fieldId)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            fieldService.DeleteFields(fieldId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
