package employee;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import employee.config.PersistenceJPAConfig;
import employee.config.EmployeeConfig;

public class EmployeeInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(final ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(EmployeeConfig.class, PersistenceJPAConfig.class);
        context.setServletContext(container);

        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher",
                                                                      new DispatcherServlet(context));

        //spring container will be initialized on app server (tomcat etc) startup.
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
