# Practica1TAP

## Esturctura/Classes

* User
    + username
    + nom
    + naixement

* Message
    + tema/títol
    + text
    + de: User envia (username)
    + per: User rep (username)
    + data i hora creació

* MailStore
    + funct `void sendMail(Message mail)`
    + funct `Message[] getMail(User user)`
    + **InMemoryMailStore**: Guarda els correus en memòria, estructura missatges ordenats per destinatari
    + **OnFileMailStore**: Missatges al final d'un arxiu, 1 missatge/línia, camps separats per `;`

* Mailbox
    + User
    + llista Messages rebuts
    + referència MailStore
    + funct `void updateMail()` actualitza llista de missatges rebuts ordre segons MailStore -> ordenar de nou a vell (Streams)
    + funct `Message[] listMail()` llista de missatges rebuts actual
    + funct `void sendMail(User desti, String subj, String text)` envia missatge a MailStore
    + funct `Message[] listMailOrder(Order order)` llista de missatges ordenada segons el valor `order` (Streams)
    + Ha de ser iterable pels missatges

* MailSystem
    + MailStore
    + User[]
    + Mailbox[]
    + funcions amb Streams (?)
    + funct `Mailbox newUser(User user)`
    + funct `Message[] getAllMessages()`
    + funct `User[] getAllUsers()`
    + funct `Message[] getAllMessagesFilter(Condition condition)`
    + funct `int getTotalMessages()`
    + funct `float getAvrgMessages()` -> nMessages/nUsers
    + funct `Message[][] groupMessagesSubject()` files->tema cols->missatges del tema
    + funct `int getTotalMessagesWordsFrom(name)` total paraules missatges enviats per users amb nom `name`
    + funct `Message[] getMessagesToBornBefore(year)` missatges enviats a nascuts abans de l'any `year`

