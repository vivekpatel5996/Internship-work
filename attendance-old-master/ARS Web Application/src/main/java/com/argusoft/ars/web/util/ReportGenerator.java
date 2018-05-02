/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Argus
 */
public class ReportGenerator {

    final int DEFAULT_BUFFER_SIZE = 10240;

    public void createReport(String rptPath, HashMap hm, String outFileName, JRBeanCollectionDataSource jrDS) {
        try {

            JasperPrint print = JasperFillManager.fillReport(
                    rptPath,
                    hm);

            // JasperFillManager.fillReport(inputStream, hm, connection)
            JasperExportManager.exportReportToPdfFile(print, outFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showReport(String fileName, String viewName) {
        /////// REport Code
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        File file = new File(fileName);
        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            // Open file.
            input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);

            // Init servlet response.
            response.reset();
            response.setContentType("application/pdf");
            response.setHeader("target:","_blank");
            response.setContentLength(input.available());
            response.setHeader("Content-disposition", "inline; filename=\"" + viewName + ".pdf\"");
            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

            // Write file contents to response.
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            for (int length; (length = input.read(buffer)) != -1;) {
                output.write(buffer, 0, length);
            }

            // Finalize task.
//            file.delete();
            file = null;
            output.flush();
            output.close();
            output = null;
            input.close();
            input = null;
            buffer = null;
            facesContext.responseComplete();
        // response.flushBuffer();
        // response.resetBuffer();
        //  response.reset();

        //close(output);
        //close(input);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Gently close streams.
        }
    }
}
