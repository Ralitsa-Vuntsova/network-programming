Домашно 11-a:

server:
   - Message.java - клас, който представлява съобщение
   - MessagePool.java - интерфейс, който описва поведението на пуул-а от съобщения 
   - MessagePoolImpl.java - имплементация на горния интерфейс
   - MessagePoolServer.java - сървър, който експортва обект и го регистрира в RMI регистъра, за да бъде видим
   - MessageQueue.java - клас, който представлява опашка от съобщения
client:
   - MessagePutClient.java - клиент, който изпраща съобщения
   - MessageGetClient.java - клиент, който получава съобщения
exception:
   - QueueEmptyException.java - изключение за празна опашка
   - QueueFullException.java - изключение за препълнена опашка

Как се стартират програмите:
0) Първо целият проект трябва да се компилира.
1) В директорията на сървъра се run-ва командата rmiregistry.
2) MessagePoolServer
- стартира се като се run-не main метода
3) MessagePutClient
- стартира се като се run-не main метода
4) MessageGetClient
- стартира се като се run-не main метода

Програмите работят на 100%.