package kane.exercise.simpleoctess4j;

import java.io.File;
import java.io.IOException;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import static org.springframework.util.ResourceUtils.CLASSPATH_URL_PREFIX;

/**
 * @author kane
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ResourceLoader resourceLoader = new ClassRelativeResourceLoader(Main.class);
        Resource imageFileResource = resourceLoader.getResource(CLASSPATH_URL_PREFIX + "/captcha/test.gif");
        File imageFile = imageFileResource.getFile();
        Resource tessdataResource = resourceLoader.getResource(CLASSPATH_URL_PREFIX + "/tessdata");
        Tesseract tessreact = new Tesseract();
        tessreact.setDatapath(tessdataResource.getFile().getAbsolutePath());
        tessreact.setLanguage("eng");
        String result;
        try {
            result = "测验结果：" + tessreact.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }
}