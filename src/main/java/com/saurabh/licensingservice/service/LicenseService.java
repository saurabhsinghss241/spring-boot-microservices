package com.saurabh.licensingservice.service;

import com.saurabh.licensingservice.entity.License;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LicenseService {
    public License getLicense(String licenceId,String organizationId){
        License license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenceId);
        license.setLicenseType("full");
        license.setDescription("Software Product.");
        license.setOrganizationId(organizationId);
        license.setProductName("OStock");

        return license;
    }
    public String createLicense(License license,String organizationId){
        if(license != null){
            license.setOrganizationId(organizationId);
            return String.format("This is the post and the object is : %s",license.toString());
        }
        return null;
    }
    public String updateLicense(License license,String organizationId){
        if(license != null){
            license.setOrganizationId(organizationId);
            return String.format("This is the put and the object is : %s",license.toString());
        }
        return null;
    }

    public String deleteLicense(String licenseId,String organizationId){
        return  String.format("Deleting license with id %s for the organization %s",licenseId, organizationId);
    }
}
