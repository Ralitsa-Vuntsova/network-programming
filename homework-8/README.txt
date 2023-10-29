Домашно 8:

RemoteFile.txt - текстов файл, съдържащ текст
URLClient.java - java файл; URLClient представлява клиент, който се свръзва с web сървър и поисква от него документ, след което прочита документа като го получи (използвайки сокет)
RemoteFileClient.java - java файл; RemoteFileClient представлява клиент, който се свръзва със сървър и поисква от него документ, след което прочита документа като го получи (използвайки сокет)
RemoteFileServer.java - java файл; RemoteFileServer представлява сървър, който получава заявка, обработва я и изпраща обратно информация на клиента (използвайки сокет)
MultithreadedRemoteFileServer.java - java файл; MultithreadedRemoteFileServer представлява сървър, който получава заявки от много клиенти, обработва ги и изпраща обратно информация на клиентите (използвайки сокети)
ConnectionHandler.java - java файл; ConnectionHandler е клас, който позволява да се стартират нишки, които да обработват заявки
PoolRemoteFileServer.java - java файл; PoolRemoteFileServer представлява сървър, който получава заявки, вкарва ги в пуул, обработва ги и изпраща обратно информация на клиентите (използвайки сокети)
PoolConnectionHandler.java - java файл; PoolConnectionHandler е клас, който позволява да се стартират нишки, които да обработват заявки (само една, другите чакат)

Как се стартират програмите:
1) URLClient:
- стартира се main метода
2) RemoveFileClient + RemoteFileServer
- стартира се main метода на RemoteFileServer
- стартира се main метода на RemoteFileClient
3) RemoveFileClient + MultithreadedRemoteFileServer
- стартира се main метода на MultithreadedRemoteFileServer
- стартира се main метода на RemoteFileClient
4) RemoveFileClient + PoolRemoteFileServer
- стартира се main метода на PoolRemoteFileServer
- стартира се main метода на RemoteFileClient

Програмите работят на 100%.