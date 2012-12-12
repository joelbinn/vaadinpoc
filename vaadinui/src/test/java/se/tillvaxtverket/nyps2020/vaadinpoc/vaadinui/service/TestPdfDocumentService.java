package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.service;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class TestPdfDocumentService {
    private PdfDocumentService pdfDocumentService;

    @Before
    public void before() {
        pdfDocumentService = new PdfDocumentService();
    }

    @After
    public void after() throws IOException {
        FileUtils.forceDelete(new File(".preview-cache"));
        FileUtils.forceDelete(new File("scratch"));
    }

    @Test
    public void testGetPreview() throws URISyntaxException, IOException {
        List<File> preview = pdfDocumentService.getPreview(
                new File(ClassLoader.getSystemResource("test.pdf").getFile()));
        assertEquals(2, preview.size());
        assertEquals("page#1.png", preview.get(0).getName());
        assertEquals("page#2.png", preview.get(1).getName());

        long time = preview.get(0).lastModified();
        preview = pdfDocumentService.getPreview(
                new File(ClassLoader.getSystemResource("test.pdf").getFile()));
        assertEquals(2, preview.size());
        assertEquals(time, preview.get(0).lastModified());
    }
}
