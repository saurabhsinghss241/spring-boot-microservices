package com.saurabh.licensingservice.service;

import com.saurabh.licensingservice.entity.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;

@Service
public class LicenseService {
    @Autowired
    MessageSource messageSource;

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
    public String createLicense(License license, String organizationId, Locale locale){
        if(license != null){
            license.setOrganizationId(organizationId);
            //based on the locale text in the respective lang will be autopopulated.
            return String.format(messageSource.getMessage("license.create.message",null,locale),license.toString());
            //return String.format("This is the post and the object is : %s",license.toString());
        }
        return null;
    }
    public String updateLicense(License license,String organizationId,Locale locale){
        if(license != null){
            license.setOrganizationId(organizationId);
            return String.format(messageSource.getMessage("license.update.message",null,locale),license.toString());
            //return String.format("This is the put and the object is : %s",license.toString());
        }
        return null;
    }

    public String deleteLicense(String licenseId,String organizationId,Locale locale){
        return String.format(messageSource.getMessage("license.delete.message",null,locale),licenseId,organizationId);
        //return  String.format("Deleting license with id %s for the organization %s",licenseId, organizationId);
    }
}
