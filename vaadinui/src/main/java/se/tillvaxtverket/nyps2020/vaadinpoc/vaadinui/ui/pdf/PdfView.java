/*
 * User: joel
 * Date: 2012-12-05
 * Time: 16:40
 */
package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.pdf;

import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.VerticalLayout;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.application.SessionAppData;

import java.io.InputStream;

public class PdfView extends VerticalLayout {
    public PdfView() {
        Embedded pdf = new Embedded("PDF", new StreamResource(new StreamResource.StreamSource() {
            public InputStream getStream() {
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("test.pdf");
                return is;
            }
        }, "file.pdf", SessionAppData.getApplication()));

        pdf.setType(Embedded.TYPE_BROWSER);
        pdf.setMimeType("application/pdf");
        pdf.setSizeFull();

        addComponent(pdf);
    }
}
