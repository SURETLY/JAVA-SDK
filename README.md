# JAVA-SDK

SDK for Suretly Lender API
## Установка
Для подключения SDK необходимо скопировать  папку с SDK в проект и подключить файл Suretly.java в месте использования SDK.
## Подключение
    Suretly suretly = new Suretly();
    suretly.init(id, token);
 
## Вызов методов API через SDK
 
### #1 Общие методы
 
#### #1.1 Получение параметров для поиска поручителей
 
    suretly.getOption().subscribe(options -> options);
#### #1.2 Список заявок
 
    suretly.getOrders(long from, long to, int limit, int skip).subscribe(orders -> orders);

### #2 Создание и работа с заявками
 
#### #2.2 Создать заявку на поручительство

    suretly.orderNew(id, false, new Borrower, user_credit_score, loan_sum, loan_rate, loan_term, currency_code, server_id).subscribe(responseOrderNew -> responseOrderNew);
   
#### #2.3 Получить статус заявки
 
    suretly.getOrderStatus(orderId).subscribe(orderStatus ->  orderStatus);
   
#### #2.4 Отменить заявку
   
    suretly.setOrderStop(orderId).subscribe(response -> response);
   
#### #2.9 Получить контракт для заемщика
 
    suretly.getContract(orderId).subscribe(contract -> contract);
   
#### #2.10 Подтвердить что договор по заявке подписан заемщиком
 
    suretly.setContractAccept(orderId).subscribe(response -> response);
   
#### #2.11 Подтвердить что заявка оплачена и выдана
 
    suretly.setOrderIssued(orderId).subscribe(response -> response);
   
### #3 Работа с оплатой заявки
 
#### #3.5 Отметить займ как выплаченный
 
    suretly.setOrderPaid(orderId).subscribe(response -> response);
   
#### #3.6 Отметить займ как выплаченный частично
 
    suretly.setOrderParialPaid(orderId, sum).subscribe(response -> response);
   
#### #3.7 Отметить займ как просроченный
 
    suretly.setOrderUnPaid(orderID).subscribe(response -> response);
   
### Справочники
 
#### Валюты
 
    suretly.getCurrencies().subscribe(currencies -> currencies);
   
#### Страны

    suretly.getCountries().subscribe(countries -> countries);
