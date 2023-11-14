package co.edu.uniquindio.servicios.impl;

import co.edu.uniquindio.servicios.interfaces.ImagenServicio;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
@Service
public class ImagenServicioImpl implements ImagenServicio {

    private final Cloudinary cloudinary;

    public ImagenServicioImpl(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dyr9bwc7g");
        config.put("api_key", "321999241656739");
        config.put("api_secret", "***************************");

        cloudinary = new Cloudinary(config);
    }
    @Override
    public Map subirImagen(MultipartFile imagen) throws Exception {
        File file = convertir(imagen);
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("AnClinica", "uniquindio/proyecto/fotos"));
    }

    @Override
    public Map eliminarImagen(String idImagen) throws Exception {
        return cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
    }

    private File convertir(MultipartFile imagen) throws Exception {
        File file = File.createTempFile(imagen.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }
}
