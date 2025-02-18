package ru.practicum.blog;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.practicum.blog.config.ApplicationConfig;

import java.io.File;
import java.nio.file.Files;
import java.util.Optional;

public class Application {

    public static void main(String[] args) throws Exception {
        startTomcat();
    }

    static void startTomcat() throws Exception {
        Tomcat tomcat = new Tomcat();
        int port = Optional.ofNullable(System.getenv("PORT")).map(Integer::parseInt).orElse(8080);
        tomcat.getConnector().setPort(port);
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(ApplicationConfig.class);

        File baseDir = Files.createTempDirectory("embedded-tomcat").toFile();
        // Create a DispatcherServlet and register it with Tomcat
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);
        Context context = tomcat.addWebapp("",baseDir.getAbsolutePath());
        context.setAllowCasualMultipartParsing(true);

        Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet).setLoadOnStartup(1);
        context.addServletMappingDecoded("/*", "dispatcherServlet");

        tomcat.start();
        tomcat.getServer().await();
    }
}
