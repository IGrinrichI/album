package org.example.album;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.ArrayList;

@Controller
public class PhotosController {

    public class ImgData{
        public String path;
        public int height;
        public int width;

        public ImgData(String path, int height, int width){
            this.path = path;
            this.height = height;
            this.width = width;
        }
    }

    @GetMapping("/photos")
    public String photos(@RequestParam(name="name", required=false, defaultValue="") String name, Model model) {
        String path = System.getProperty("user.dir") + "/src/main/resources/static/images/" + name;
        File myFolder = new File(path);
        File[] files = myFolder.listFiles();
        ArrayList<ImgData> images = new ArrayList<ImgData>();
        /*BufferedImage bimg = ImageIO.read(new File(filename));
        int width          = bimg.getWidth();
        int height         = bimg.getHeight();*/
        for(int i = 0; i < files.length; i++){
            if(!files[i].isDirectory()){
                images.add(new ImgData("/images/" + name + "/" + files[i].getName(),66,66));
            }
        }
        model.addAttribute("name", images);
        return "photos";
    }

}