package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.docpreview;

import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.service.PdfDocumentService;

import javax.inject.Inject;
import java.io.IOException;

public class DocPreviewViewFactory {
    @Inject
    private PdfDocumentService pdfDocumentService;

    public DocPreviewView newView() throws IOException {
        return new DocPreviewView(pdfDocumentService);
    }
}
