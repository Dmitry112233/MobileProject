package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

@Log4j
public class XmlUtil<T> {

    public void saveToXmlFile(String filePath, T objectToSave) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xmlString = "";
            xmlString += xmlMapper.writeValueAsString(objectToSave);

            File xmlOutput = new File(filePath);
            FileWriter fileWriter = new FileWriter(xmlOutput);
            fileWriter.write(xmlString);
            fileWriter.close();
        } catch (IOException e) {
            log.debug("Exception after try to save object int the XML: " + filePath);
            e.printStackTrace();
        }
    }

    public T getFromXML(String filePath, Class<T> myClass) {
        T objects = null;
        try {
            ObjectMapper objectMapper = new XmlMapper();
            objects = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes
                    (Paths.get(filePath)), StandardCharsets.UTF_8), myClass);
        } catch (NoSuchFileException e) {
            log.debug("Xml file doesn't exist");
        } catch (IOException e) {
            log.debug("Exception after try to read object from XML, possibly this file isn't exist: " + filePath);
            e.printStackTrace();
        }
        return objects;
    }

}
