package com.clevertec.strezhik.utils;

import com.clevertec.strezhik.Main;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResourcesReader {

    private String url;
    private String user;
    private String password;
    private String driver;

    public static ResourcesReader getResourcesReader() {
        URL resource = Main.class.getResource("/application.yaml");
        ResourcesReader resourcesReader = null;
        try {
            String path = Paths.get(resource.toURI()).toString();
            File file = new File(path);
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            resourcesReader = om.readValue(file, ResourcesReader.class);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return resourcesReader;
    }
}