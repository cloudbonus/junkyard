package com.github.poi.service;

import com.github.poi.domain.EmployeeDetails;
import com.github.poi.domain.Salary;
import com.github.poi.domain.VariableTypes;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Raman Haurylau
 */
@Component
public class DocumentServiceImpl implements DocumentService {

    Logger log = LoggerFactory.getLogger(DocumentServiceImpl.class);

    public ByteArrayOutputStream createDocument(Integer id) throws URISyntaxException, IOException {

        EmployeeDetails employeeDetails = EmployeeDetails.builder()
                .firstName("Ranil")
                .lastName("Perera")
                .address("100,Temple Street,Colombo")
                .dob(LocalDate.now().minusYears(26))
                .employeeId(id)
                .gender("Male")
                .position("Software Engineer")
                .build();

        String resourcePath = "template.docx";
        Path templatePath = Paths.get(Objects.requireNonNull(DocumentServiceImpl.class.getClassLoader().getResource(resourcePath)).toURI());
        XWPFDocument doc = new XWPFDocument(Files.newInputStream(templatePath));

        Map<String, String> map = Map.of(
                VariableTypes.FIRST_NAME.getName(), employeeDetails.getFirstName(),
                VariableTypes.LAST_NAME.getName(), employeeDetails.getLastName(),
                VariableTypes.POSITION.getName(), employeeDetails.getPosition(),
                VariableTypes.GENDER.getName(), employeeDetails.getGender(),
                VariableTypes.DATE_OF_BIRTH.getName(), employeeDetails.getDob().toString(),
                VariableTypes.ADDRESS.getName(), employeeDetails.getAddress(),
                VariableTypes.EMPLOYEE_ID.getName(), employeeDetails.getEmployeeId().toString()
        );

        replaceTextFor(doc, map);

        List<Salary> salaryRecordList = Arrays.asList(
                Salary.builder().month("Jan 2020").amount(String.valueOf(1200.30)).build(),
                Salary.builder().month("Feb 2020").amount(String.valueOf(1200.30)).build(),
                Salary.builder().month("Mar 2020").amount(String.valueOf(1200.30)).build(),
                Salary.builder().month("Apr 2020").amount(String.valueOf(1200.30)).build(),
                Salary.builder().month("May 2020").amount(String.valueOf(1500.70)).build(),
                Salary.builder().month("Jun 2020").amount(String.valueOf(1500.70)).build()
        );

        replaceSalaryTable(doc, salaryRecordList);

        savePdf(doc);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfOptions options = PdfOptions.create();
        PdfConverter.getInstance().convert(doc, out, options);
        out.close();
        return out;
    }

    private void replaceTextFor(XWPFDocument doc, Map<String, String> map) {
        doc.getParagraphs().forEach(p -> p.getRuns().forEach(run -> {
            String text = run.text();
            map.forEach((findText, replaceText) -> {
                if (text.contains(findText)) {
                    run.setText(text.replace(findText, replaceText), 0);
                }
            });
        }));
    }

    private void savePdf(XWPFDocument doc) {

        try (OutputStream out = new FileOutputStream("/home/user/repos/junkyard/poi/src/main/resources/employee-report.pdf")) {
            PdfOptions options = PdfOptions.create().fontEncoding("UTF-8");
            PdfConverter.getInstance().convert(doc, out, options);
        } catch (Exception e) {
            log.error("Ops!", e);
        }
    }

    public void replaceSalaryTable(XWPFDocument doc, List<Salary> salaryRecordList) {
        XWPFTable table = doc.getTableArray(0);
        int templateRowId = 1;
        XWPFTableRow rowTemplate = table.getRow(templateRowId);

        salaryRecordList.forEach(salaryRecord -> {

            CTRow ctrow = null;
            try {
                ctrow = CTRow.Factory.parse(rowTemplate.getCtRow().newInputStream());
            } catch (XmlException | IOException e) {
                log.error("Ops!", e);
            }

            XWPFTableRow newRow = new XWPFTableRow(ctrow, table);

            newRow.getCell(0).getParagraphArray(0).getRuns().getFirst().setText(salaryRecord.getMonth(), 0);
            newRow.getCell(1).getParagraphArray(0).getRuns().getFirst().setText(salaryRecord.getAmount(), 0);

            table.addRow(newRow);
        });

        table.removeRow(templateRowId);
    }
}