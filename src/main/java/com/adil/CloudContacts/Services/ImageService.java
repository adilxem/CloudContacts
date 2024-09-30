package com.adil.CloudContacts.Services;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.adil.CloudContacts.Helper.Constants;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ImageService {

    @Autowired
    Cloudinary cloudinary;

    public String uploadImage(MultipartFile contactImage, String fileName) {



        try {

            byte[] data = new byte[contactImage.getInputStream().available()];

            contactImage.getInputStream().read(data);

            cloudinary.uploader().upload(data, ObjectUtils.asMap("public_id", fileName));

            return this.getUrlFromPublicId(fileName);
        } 
        
        catch (IOException e) {

            e.printStackTrace();

            return null;
        }

    }

    public String getUrlFromPublicId(String publicId) {

        return cloudinary
        .url()
        .transformation(new Transformation<>()
        .width(Constants.CONTACT_IMAGE_HEIGHT)
        .height(Constants.CONTACT_IMAGE_HEIGHT)
        .crop(Constants.CONTACT_IMAGE_CROP))
        .generate(publicId);
    }

}
