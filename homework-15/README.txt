Домашно 15:

ThreeParamsServlet - dynamic web application, част 1 от домашното
ArchiveServlet - dynamic web application, част 2 от домашното
homework - директория, в която има поддиректории, които съдържат файлове; dir1, dir2 и dir3 ще бъдат архивирани; след това ще бъдат разархивирани в downloaded

Как се стартира програмaтa:
0) Prerequizites:
- eclipse installed
- tomcat server installed and configured in eclipse
- homework dir needs to be copied into disc E
1) ThreeParamsServlet
- index.html файла се run-ва чрез tomcat сървъра
2) ArchiveServlet
- ArchiveServlet-a се run-ва чрез tomcat сървъра, като тук http://localhost:8080/ArchiveServlet/ArchiveServlet не ни интересува, тъй като клиента ще го извиква с подадени dir и filter
- TCPClient се стартира като java application, като тук това, което се случва е:
	- Клиента казва, че иска да му се пратят архивирани homework/dir1, homework/dir2 и homework/dir3 (само .txt файлове).
	- Сървъра ги архивира в папката homework и ги изпраща на клиента.
	- Клиента ги разрхивира в homework/downloaded.

Програмите работят на 100%.