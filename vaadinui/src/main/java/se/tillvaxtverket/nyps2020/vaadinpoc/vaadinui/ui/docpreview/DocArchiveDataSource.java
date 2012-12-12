package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.docpreview;

import com.vaadin.data.util.BeanItemContainer;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DocArchiveDataSource extends BeanItemContainer<File> {
    private final File docDir;

    public DocArchiveDataSource(File docDir) throws IOException {
        super(File.class);
        this.docDir = docDir != null ? docDir : new File("/tmp/doc-archive");
        FileUtils.forceMkdir(this.docDir);
        loadFiles();
    }

    public DocArchiveDataSource() throws IOException {
        this(null);
    }

    private void loadFiles() {
        addAll(FileUtils.listFiles(docDir, new String[]{"pdf"}, false));
    }

    public void refresh() {
        removeAllItems();
        loadFiles();
    }

    public OutputStream createOutputStreamForWriting(String filename,
                                                     String mimeType) {
        if (!"application/pdf".equals(mimeType)) {
            return null;
        }

        try {
            if (FileUtils.directoryContains(docDir, new File(filename))) {
                return null;
            }
            return new FileOutputStream(new File(docDir, filename));
        } catch (IOException e) {
            return null;
        }
    }
}
