import java.io.*;

public class PersonalCard {
    public static void createCard(String photo, String name, String email, String note) throws IOException {
        File file = new File("personalCard.vcf");
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        String info = "BEGIN:VCARD\n" +
                      "PHOTO;TYPE=JPEG;VALUE=URI:" + photo + "\n" +
                      "FN:" + name + "\n" +
                      "EMAIL:" + email + "\n" +
                      "NOTE:" + note + "\n" +
                      "END:VCARD\n";

        fileOutputStream.write(info.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public static void main(String[] args) throws IOException {
        String photo = "https://media-exp1.licdn.com/dms/image/C5603AQFVteJVzK-JtQ/profile-displayphoto-shrink_800_800/0/1617179589120?e=1671667200&v=beta&t=JPZF1bwh40z8QaXPccusI2I38kL0Bg9vrLluyxq4e4A";
        String name = "Ralitsa Vuntsova";
        String email = "ralitsavun@gmail.com";
        String note = "I am a student currently majoring in Information security in computer systems and networks" +
                      "Programming languages I am familiar with are: Ruby, JavaScript, C++, C and Java";

        createCard(photo, name, email, note);
    }
}
