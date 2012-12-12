package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.service;

import com.vaadin.terminal.FileResource;
import com.vaadin.ui.Embedded;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFImageWriter;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.application.SessionAppData;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PdfDocumentService {
    private String previewCacheDirName = "/tmp/.preview-cache";

    public List<File> getPreview(File docFile) throws IOException {
        List<File> imageFiles;
        if ((imageFiles = getCachedPreview(docFile)).size() == 0) {
            imageFiles = createPreview(docFile);
        }
        Collections.sort(imageFiles);
        return imageFiles;
    }

    private List<File> getCachedPreview(File docFile) throws IOException {
        File[] cachedFiles = getCachedFiles(docFile);
        cachedFiles = cachedFiles != null ? cachedFiles : new File[0];
        List<File> images = new ArrayList<File>();
        for (File cachedFile : cachedFiles) {
            if (cachedFile.lastModified() < docFile.lastModified()) {
                return Collections.emptyList();
            }
            images.add(cachedFile);
        }
        return images;
    }

    private File[] getCachedFiles(File docFile) throws IOException {
        File cacheDirForFile = getCacheDirForFile(docFile);
        FileUtils.forceMkdir(cacheDirForFile);
        return cacheDirForFile.listFiles();
    }

    private File getCacheDirForFile(File docFile) {
        return new File(previewCacheDirName, docFile.getName());
    }

    private List<File> createPreview(File docFile) throws IOException {
        PDFImageWriter imageWriter = new PDFImageWriter();
        PDDocument document = PDDocument.loadNonSeq(docFile,
                new RandomAccessFile(new File("scratch"), "rw"));
        File cacheDirForFile = getCacheDirForFile(docFile);
        if (!imageWriter.writeImage(
                document, "png", null, 1, 2, new File(cacheDirForFile,"page#").getAbsolutePath(),
                BufferedImage.TYPE_BYTE_GRAY, 36)) {
            throw new RuntimeException("Failed to write PDF as image");
        }
        return getCachedPreview(docFile);
    }
}
