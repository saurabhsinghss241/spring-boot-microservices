package com.saurabh.licensingservice.controller;

import com.saurabh.licensingservice.entity.License;
import com.saurabh.licensingservice.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/v1/organization/{organizationId}/license")
public class LicenseController {
    @Autowired
    private LicenseService licenseService;

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(
            @PathVariable(name = "organizationId") String organizationId,
            @PathVariable(name="licenseId") String licenseId
            ) {
        License license = licenseService.getLicense(licenseId,organizationId);

        return new ResponseEntity<>(license, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> createLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License license,
            @RequestHeader(value = "Accept-Language",required = false) Locale locale
            ){
        String result = licenseService.createLicense(license,organizationId,locale);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<String> updateLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License license,
            @RequestHeader(value = "Accept-Language",required = false) Locale locale
    ){
        String result = licenseService.updateLicense(license,organizationId,locale);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable(name = "organizationId") String organizationId,
            @PathVariable(name="licenseId") String licenseId
    ) {
        String result = licenseService.deleteLicense(licenseId,organizationId);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
