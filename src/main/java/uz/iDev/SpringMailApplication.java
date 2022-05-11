    package uz.iDev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.EventListener;
import uz.iDev.config.ProviderConfiguration;
import uz.iDev.model.Email;
import uz.iDev.service.EmailServiceImpl;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({
        ProviderConfiguration.class
})
public class SpringMailApplication {

    @Autowired
    private EmailServiceImpl emailService;

    public static void main(String[] args) {
        SpringApplication.run(SpringMailApplication.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void sendMessage(){
        String msg = """
                John Smith
                Via Torquato Tasso, 26
                71010 Lesina FG, USA
                                
                                
                March 22, 2019
                                
                Dear John Smith,
                                
                I would like to invite you to come visit me in the United States this summer. You are welcome to stay at my home during for the duration of the trip. I understand that you have four weeks of holiday, but you are welcome to stay longer if needed.
                                
                During your visit, I plan to take you on several excursions to our theme parks (Disneyworld and Universal Orlando) and museums. I know you son is interested in space travel. I’m trying to arrange a visit to the Kennedy Space Center Visitor Complex as well.
                                
                I understand you plan to cover your own expenses for travel and incidentals. However, I will submit Form I-134, Affidavit of Support, to guarantee your financial support.
                                
                I look forward to seeing you and your family.
                                
                Sincerely,
                                
                Edwardo Sauvigne
                26000 Whispering Trails Ave
                Winter Have, FL 33884
                Home: (863) 555-1212
                Mobile: (863) 555-1212
                """;
        Email email = new Email();
        email.setSubject("USA");
        email.setBody(msg);
        email.setRecipients(List.of("johndoev7077@gmail.com", "jamessmith12@gmail.com","janedoe122@gmail.com"));
        email.setIsHtml(false);

        emailService.sendSimpleMessage(email);
    }

}
