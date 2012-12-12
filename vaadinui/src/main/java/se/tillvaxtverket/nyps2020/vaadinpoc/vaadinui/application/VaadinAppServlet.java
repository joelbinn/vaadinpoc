/*
 * User: joel
 * Date: 2012-11-19
 * Time: 22:39
 */
package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.application;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.ApplicationServlet;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet(urlPatterns = "/*", initParams = {
        @WebInitParam(name = "application", value = "se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.application.VaadinInEarUiApplication")
})
public class VaadinAppServlet extends ApplicationServlet {

    @Inject
    Instance<VaadinInEarUiApplication> application;

    @Override
    protected Class<? extends Application> getApplicationClass() throws ClassNotFoundException {
        return VaadinInEarUiApplication.class;
    }

    @Override
    protected Application getNewApplication(HttpServletRequest request) throws ServletException {
        return application.get();
    }
}
