# Practica1TAP
## Esturctura/Classes

* part1.User
    + username
    + nom
    + naixement

* part1.Message
    + tema/títol
    + text
    + de: part1.User envia (username)
    + per: part1.User rep (username)
    + data i hora creació

* part1.MailStore
    + funct `void sendMail(Message mail)`
    + funct `Message[] getMail(User user)`
    + **part1.InMemoryMailStore**: Guarda els correus en memòria, estructura missatges ordenats per destinatari
    + **part1.OnFileMailStore**: Missatges al final d'un arxiu, 1 missatge/línia, camps separats per `;`

* Mailbox
    + User
    + llista Messages rebuts
    + referència MailStore
    + funct `void updateMail()` actualitza llista de missatges rebuts ordre segons part1.MailStore -> ordenar de nou a vell (Streams)
    + funct `part1.Message[] listMail()` llista de missatges rebuts actual
    + funct `void sendMail(User desti, String subj, String text)` envia missatge a part1.MailStore
    + funct `part1.Message[] listMailOrder(Order order)` llista de missatges ordenada segons el valor `order` (Streams)
    + Ha de ser iterable pels missatges

* part1.MailSystem
    + MailStore
    + User[]
    + Mailbox[]
    + funcions amb Streams (?)
    + funct `Mailbox newUser(part1.User user)`
    + funct `part1.Message[] getAllMessages()`
    + funct `part1.User[] getAllUsers()`
    + funct `part1.Message[] getAllMessagesFilter(Condition condition)`
    + funct `int getTotalMessages()`
    + funct `float getAvrgMessages()` -> nMessages/nUsers
    + funct `part1.Message[][] groupMessagesSubject()` files->tema cols->missatges del tema
    + funct `int getTotalMessagesWordsFrom(name)` total paraules missatges enviats per users amb nom `name`
    + funct `part1.Message[] getMessagesToBornBefore(year)` missatges enviats a nascuts abans de l'any `year`

