package com.lib.emv.co.MP.Controllers;

import com.lib.emv.co.MP.Dto.QrCodeRequest;

import com.lib.emv.co.MP.Service.DataProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tlv")
public class QrCodeController {

    private final DataProcessing dataProcessing;

    @Autowired
    public QrCodeController(DataProcessing dataProcessing ) {
        this.dataProcessing = dataProcessing;

    }@PostMapping("/Static")
    public ResponseEntity<String> generateStaticCode(@RequestBody QrCodeRequest request) {
        request.setPointOfInitiationMethod("11");

        String qrCode = dataProcessing.dataValidation(request);
        return new ResponseEntity<>(qrCode, HttpStatus.OK);
    }
    @PostMapping("/Dynamic")
    public ResponseEntity<String> generateDynamicCode(@RequestBody QrCodeRequest request) {
        request.setPointOfInitiationMethod("12");
      //  String qrCode = service.TLV(request);
        String qrCode = dataProcessing.dataValidation(request);
        return new ResponseEntity<>(qrCode, HttpStatus.OK);
    }
}

// for the customer id in the tag 28  Sub Tag 00: GUID - A [UUID] without the
//hyphen (-) separators.For example,“581b314e257f41bfbbdc6384daa31d16
/*

* */
// LEAVE THE TAGS IF NO VALUS IS ADDED IN THE PAYLOAD BUT THA TAG NAME IS THERE
// LEAVE THE TAG IF THE TAG NAME IS NOT PRESENT IN THE PAYLOAD
// CHARACTER LIMITS AND RESPONSES
// DISCIUNT AND LOYALITY PROGRAMS  TAGS 81


