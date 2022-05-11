package uz.iDev.model;

import jdk.jfr.Description;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Description(value = "Class that represents Email transfer object")
public class Email {
    private List<String> recipients;
    private List<String> ccList;
    private List<String> bccList;
    private String subject;
    private String body;
    private Boolean isHtml;
    private String attachmentPath;

}
