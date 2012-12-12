package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.docpreview;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.application.SessionAppData;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.service.PdfDocumentService;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.util.WarningEvent;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

public class DocPreviewView extends HorizontalLayout
        implements Button.ClickListener, Upload.Receiver,
        Upload.SucceededListener, Upload.FailedListener,
        ItemClickEvent.ItemClickListener {
    private static final ThemeResource PREV_ICON =
            new ThemeResource("images/icons/arrow-left.png");
    private static final ThemeResource NEXT_ICON =
            new ThemeResource("images/icons/arrow-right.png");
    private Table docTable;
    private Embedded image = new Embedded();
    private Button next;
    private Button prev;
    private List<File> imageFiles = Collections.emptyList();
    private int index = -1;
    private final PdfDocumentService pdfDocumentService;
    private final DocArchiveDataSource docArchiveDataSource;

    public DocPreviewView(PdfDocumentService pdfDocumentService)
            throws IOException {
        this.pdfDocumentService = pdfDocumentService;
        docArchiveDataSource = new DocArchiveDataSource();
        setSizeFull();
        setMargin(true);
        setSpacing(true);
        addStyleName("docpreview");

        VerticalLayout leftSide = createLeftSide();
        addComponent(leftSide);

        VerticalLayout rightSide = createRightSide();
        addComponent(rightSide);
    }

    private VerticalLayout createRightSide() {
        VerticalLayout rightSide = new VerticalLayout();
        rightSide.addComponent(image);
        rightSide.setExpandRatio(image, 1.0f);
        rightSide.setComponentAlignment(image, Alignment.MIDDLE_CENTER);

        HorizontalLayout buttonBar = new HorizontalLayout();
        buttonBar.setSpacing(true);
        buttonBar.setMargin(true);
        buttonBar.addStyleName("navigation");

        next = new Button(SessionAppData.getLocalizedString(
                "docpreview.next_page"), this);
        next.setIcon(NEXT_ICON);
        prev = new Button(SessionAppData.getLocalizedString(
                "docpreview.prev_page"), this);
        prev.setIcon(PREV_ICON);
        buttonBar.addComponent(prev);
        buttonBar.addComponent(next);

        rightSide.addComponent(buttonBar);
        rightSide.setComponentAlignment(buttonBar, Alignment.MIDDLE_CENTER);
        return rightSide;
    }

    private VerticalLayout createLeftSide() throws IOException {
        VerticalLayout leftSide = new VerticalLayout();
        Upload upload = new Upload(
                SessionAppData.getLocalizedString("docpreview.upload_doc"),
                this);
        upload.addListener((Upload.SucceededListener) this);
        upload.addListener((Upload.FailedListener) this);
        leftSide.addComponent(upload);

        docTable = new Table(SessionAppData.getLocalizedString(
                "docpreview.document_table_title"),
                docArchiveDataSource);
        docTable.addListener(this);
        docTable.setVisibleColumns(new Object[]{"name"});
        docTable.setColumnHeaders(new String[]{"Filnamn"});
        docTable.setPageLength(5);
        docTable.setWidth("100%");
        leftSide.addComponent(docTable);
        return leftSide;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (next == event.getSource()) {
            index++;
            index = imageFiles.size() == 0 ? -1 : index % imageFiles.size();
        } else if (prev == event.getSource()) {
            index--;
            index = index < 0 ? imageFiles.size() - 1 : index;
        }

        updatePageImage();
    }

    public void setDocument(File docFile) {
        try {
            imageFiles = pdfDocumentService.getPreview(docFile);
            index = 0;
            updatePageImage();
        } catch (Throwable e) {
            SessionAppData.getEventBus().fireEvent(
                    new WarningEvent(
                            this,
                            SessionAppData.getLocalizedString(
                                    "docpreview.preview_load_fail",
                                    docFile),
                            e));
        }
    }

    private void updatePageImage() {
        if (imageFiles.size() > 0) {
            image.setSource(new FileResource(imageFiles.get(index),
                    SessionAppData.getApplication()));
            image.setCaption(
                    SessionAppData
                            .getLocalizedString("docpreview.page", index + 1));
        }
    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        return docArchiveDataSource.createOutputStreamForWriting(filename,
                mimeType);
    }

    @Override
    public void uploadFailed(Upload.FailedEvent event) {
        SessionAppData.getEventBus().fireEvent(
                new WarningEvent(
                        this,
                        SessionAppData.getLocalizedString(
                                "docpreview.upload_fail",
                                event.getFilename(),
                                event.getReason()),
                        null));
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent event) {
        docArchiveDataSource.refresh();
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        if (event.getSource() == docTable) {
            File docFile = (File) event.getItemId();
            setDocument(docFile);
        }
    }
}
