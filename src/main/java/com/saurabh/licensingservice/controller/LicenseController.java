package com.saurabh.licensingservice.controller;

import com.saurabh.licensingservice.entity.License;
import com.saurabh.licensingservice.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

        license.add(
                linkTo(methodOn(LicenseController.class)
                        .getLicense(organizationId, license.getLicenseId()))
                        .withSelfRel(),
                linkTo(methodOn(LicenseController.class)
                        .createLicense(organizationId, license, null))
                        .withRel("createLicense"),
                linkTo(methodOn(LicenseController.class)
                        .updateLicense(organizationId, license,null))
                        .withRel("updateLicense"),
                linkTo(methodOn(LicenseController.class)
                        .deleteLicense(organizationId, license.getLicenseId(),null))
                        .withRel("deleteLicense")
        );

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
            @PathVariable(name="licenseId") String licenseId,
            @RequestHeader(value = "Accept-Language",required = false) Locale locale
    ) {
        String result = licenseService.deleteLicense(licenseId,organizationId,locale);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
