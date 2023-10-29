Домашно 13:

MailExample.java - изпраща обикновен имейл с текст
AttachExample.java - изпраща имейл с прикачен файл
HTMLImageExample.java - изпраща имейл с HTML, който съдържа inline image
GetMessageExample.java - чете имейли
ReplyExample.java - чете имейли и ги препраща обратно с оригиналното съдържание
activation.jar - нужен е за да работи javamail api-то
javax.mail.jar - нужен е за да работи javamail api-то
random-pic.jpg - изображение, което се изпраща като прикачен файл/HTML inline image

Как се стартират програмите:

Prerequizite:
Sample email what will be used as a sender:
Username: ziksmmaster2022@gmail.com
Password:

0) Първо целият проект трябва да се компилира. Преди това, javax и activation jar файлвовете трябва да се добавят към проекта.
1) MailExample
- стартира се като се run-не main метода
2) AttachExample
- стартира се като се run-не main метода като му се подава абсолютния път на image като аргумент
3) HtmlImageExample
- стартира се като се run-не main метода като му се подава абсолютния път на image като аргумент
4) GetMessageExample
- предварително: ziksmmaster2022@gmail.com трябва да има някакви имейли в inbox-a
- стартира се като се run-не main метода
5) ReplyExampe
- предварително: ziksmmaster2022@gmail.com трябва да има някакви имейли в inbox-a
- стартира се като се run-не main метода

Програмите работят на 100%.